package samples

import spock.lang.Specification
import spock.lang.Unroll

class MathSpec extends Specification {
    def "maximum of two numbers"() {
        expect:
            Math.max(1, 3) == 3
            Math.max(7, 4) == 7
            Math.max(0, 0) == 0
    }

    @Unroll
    def "method names can have variables (a,b,c) = (#a, #b, #c)"() {
        expect:
            Math.max(a, b) == c

        where: "one value can come from a data table"
            a | _
            5 | _
            1 | _

        and: "other values can come from a data pipe - useful when data comes from a provider of some sort"
            b << [1, 9]
            c << [5, 9]
    }
}