grails.servlet.version = "3.0"
grails.project.class.dir = "target/classes"
grails.project.work.dir="target/work"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    // test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits false // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        mavenRepo "http://repo.grails.org/grails/core"
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }

    def gebVersion = "0.9.0-RC-1"
    def seleniumVersion = "2.27.0"
    def spockVersion = "0.7"

    dependencies {
        compile 'postgresql:postgresql:9.1-901-1.jdbc4'

        test( "com.github.detro.ghostdriver:phantomjsdriver:1.0.1" ) {
           transitive = false
        }

        test("org.seleniumhq.selenium:selenium-htmlunit-driver:$seleniumVersion") {
            exclude "xml-apis"
        }
        test("org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion")
        test("org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion")

        test('dumbster:dumbster:1.6') {
            excludes 'mail', 'activation'
        }

        test "org.gebish:geb-spock:$gebVersion"
        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
    }

    plugins {
        compile ':cache:1.1.1'
        compile ":cache-headers:1.1.5"
        compile ":cloud-bees:0.6.2"
        compile ":commentable:0.8.1"
        compile ":famfamfam:1.0.1"
        compile ":feeds:1.6"
        runtime ":jquery:1.10.2"
        compile ":mail:1.0.1"
        compile ":quartz:1.0-RC5"
        compile ":rateable:0.7.1"
        compile ":searchable:0.6.5-SNAPSHOT"
        compile ":seofriendly-urls:1.0.2"
        compile ":spring-security-core:1.2.7.3"
        compile ":spring-security-ui:0.2"

        test ":geb:$gebVersion"
        test(":spock:$spockVersion") {
            exclude "spock-grails-support"
        }

        runtime ":hibernate:3.6.10.1"
        runtime ":jquery:1.9.1"
        runtime ":jquery-ui:1.8.24"
        runtime ":resources:1.2"

        runtime ":zipped-resources:1.0.1"
        runtime ":cached-resources:1.1"
        runtime ":yui-minify-resources:0.1.5"

        build ":tomcat:7.0.42"
    }
}
