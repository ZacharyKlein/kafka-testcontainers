package com.example

import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.micronaut.test.support.TestPropertyProvider
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewTopic
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.containers.Network
import org.testcontainers.utility.DockerImageName
import spock.lang.Shared
import spock.lang.Specification
import jakarta.inject.Inject

@MicronautTest
class KafkatestSpec extends Specification implements TestPropertyProvider {

    @Inject
    EmbeddedApplication<?> application

    @Inject
    DemoProducer producer

    @Shared
    Network network = Network.newNetwork()

    @Shared
    KafkaContainer kafka = new KafkaContainer(DockerImageName.parse('confluentinc/cp-kafka:6.1.0'))
            .withNetwork(network)
            .withNetworkAliases('broker')

    def setupSpec() {
        if (this.kafka.running) return
        kafka.start()
    }

    void 'test it works'() {
        when:
        producer.send("foo", "bar")

        then:
        true
    }

    @Override
    Map<String, String> getProperties() {
        // make sure containers are running
        setupSpec()
        ['kafka.bootstrap.servers': kafka.bootstrapServers]
    }

}
