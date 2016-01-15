/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.indicestextfield

import static com.anrisoftware.globalpom.utils.TestUtils.*
import groovy.util.logging.Slf4j

import org.junit.Test

@Slf4j
class ArrayRangeParserTest {

    @Test
    void "parse strings"() {
        def testCases = [
            [text: "", value: [], continuesRange: false, min: 0, max: 0, str: "", ex: null],
            [text: "{}", value: [], continuesRange: false, min: 0, max: 0, str: "{}", ex: null],
            [text: "1", value: [1], continuesRange: true, min: 1, max: 1, str: "1", ex: null],
            [text: "{1}", value: [1], continuesRange: true, min: 1, max: 1, str: "{1}", ex: null],
            [text: "{1,2}", value: [1, 2], continuesRange: true, min: 1, max: 2, str: "{1,2}", ex: null],
            [text: "{1,2,3}", value: [1, 2, 3], continuesRange: true, min: 1, max: 3, str: "{1,2,3}", ex: null],
            [text: "{1,2,5}", value: [1, 2, 5], continuesRange: false, min: 1, max: 5, str: "{1,2,5}", ex: null],
            [text: "[1,5]", value: [1, 2, 3, 4, 5], continuesRange: true, min: 1, max: 5, str: "[1,5]", ex: null],
            [text: "]1,5]", value: [2, 3, 4, 5], continuesRange: true, min: 2, max: 5, str: "]1,5]", ex: null],
            [text: "(1,5]", value: [2, 3, 4, 5], continuesRange: true, min: 2, max: 5, str: "(1,5]", ex: null],
            [text: "[1,5[", value: [1, 2, 3, 4], continuesRange: true, min: 1, max: 4, str: "[1,5[", ex: null],
            [text: "[1,5)", value: [1, 2, 3, 4], continuesRange: true, min: 1, max: 4, str: "[1,5)", ex: null],
            [text: "]1,5[", value: [2, 3, 4], continuesRange: true, min: 2, max: 4, str: "]1,5[", ex: null],
            [text: "(1,5)", value: [2, 3, 4], continuesRange: true, min: 2, max: 4, str: "(1,5)", ex: null],
            [text: "{", ex: InvalidRangeException],
            [text: "[1,2,3]", ex: InvalidRangeException],
        ]
        testCases.eachWithIndex { test, int k ->
            log.info "{}. case {}", k, test
            if (test.ex == null) {
                def range = new ArrayRangeParser(test.text).parseString()
                assert range.continuesRange == test.continuesRange
                assert range.value == test.value as int[]
                assert range.min == test.min
                assert range.max == test.max
                assert range.str == test.str
            } else {
                shouldFailWith(test.ex) {
                    new ArrayRangeParser(test.text).parseString()
                }
            }
        }
    }
}
