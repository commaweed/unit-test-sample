package samples

import spock.lang.Shared
import spock.lang.Specification

/**
 * Demonstrates spock specification life cycle.
 */
class SampleSpockLifecycleSpec extends Specification {

	/* =====================================================================================
	   Fields
	   ===================================================================================== */

	// 1.  static fields:  should only be used for constants; otherwise use @shared fields
	static final INCREMENTER = 10

	// 2.  instance fields:  not shared between feature methods; every feature method gets its own object
	//     * initializing these inline is the same thing as declaring them in the setup() fixture method
	def instanceResource = new Stack()
	def instanceCounter

	// 3.  Shared fields:  shared between feature methods
	//     * intializing these inline is the same thing as declaring them in the setupSpec() fixture method
	//     * preferred over static fields
	@Shared def sharedResource = new Stack()
	@Shared def sharedCounter

	/* =====================================================================================
	   Fixture Methods - life cycle methods
	   ===================================================================================== */

	// 1.  setup:  runs before every feature method
	def setup() {
		println "setup:  executing..."
		instanceCounter = 0
		println "setup: instanceResource $instanceResource instanceCounter $instanceCounter"
	}

	// 2.  cleanup:  runs after every feature method
	def cleanup() {
		println "cleanup:  executing..."
	}

	// 3.  setupSpec:  runs before the first feature method
	//     * cannot reference instance fields
	def setupSpec() {
		println "setupSpec:  executing..."
		sharedCounter = -100
		println "setupSpec: shared $sharedResource sharedCounter $sharedCounter"
	}

	// 4.  cleanupSpec:  runs after the last feature method
	//     * cannot reference instance fields
	def cleanupSpec() {
		println "cleanupSpec:  executing..."
	}

	/* =====================================================================================
	   Feature Methods - test methods (must contain phase blocks)
	   ===================================================================================== */

	def "I am going to test thing one"() {
		setup:
			modifySharedResource()
			modifyInstanceResource()
		cleanup:
			display()
	}

	def "I am going to test thing two"() {
		setup:
			modifySharedResource()
			modifyInstanceResource()
		cleanup:
			display()
	}

	def "I am going to test thing three"() {
		setup:
			modifySharedResource()
			modifyInstanceResource()
		cleanup:
			display()
	}

	/* =====================================================================================
	   helper Methods:  non-test methods (cannot contain phase blocks)
	   ===================================================================================== */
	def modifyInstanceResource() {
		instanceCounter += INCREMENTER
		instanceResource.push(instanceCounter)
	}

	def modifySharedResource() {
		sharedCounter += INCREMENTER
		sharedResource.push(sharedCounter)
	}

	def display() {
		println "one: instanceResource [$instanceResource] instanceCounter [$instanceCounter]"
		println "one: sharedResource [$sharedResource] sharedCounter [$sharedCounter]"
	}
}

/*
setupSpec:  executing...
setupSpec: shared [] sharedCounter -100
setup:  executing...
setup: instanceResource [] instanceCounter 0
one: instanceResource [[10]] instanceCounter [10]
one: sharedResource [[-90]] sharedCounter [-90]
cleanup:  executing...
setup:  executing...
setup: instanceResource [] instanceCounter 0
one: instanceResource [[10]] instanceCounter [10]
one: sharedResource [[-90, -80]] sharedCounter [-80]
cleanup:  executing...
setup:  executing...
setup: instanceResource [] instanceCounter 0
one: instanceResource [[10]] instanceCounter [10]
one: sharedResource [[-90, -80, -70]] sharedCounter [-70]
cleanup:  executing...
cleanupSpec:  executing...
*/
