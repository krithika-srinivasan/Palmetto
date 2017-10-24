/**
 * Palmetto - Palmetto is a quality measure tool for topics.
 * Copyright © 2014 Data Science Group (DICE) (michael.roeder@uni-paderborn.de)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.aksw.palmetto.vector;

import java.util.Arrays;
import java.util.Collection;

import org.aksw.palmetto.calculations.direct.JaccardConfirmationMeasure;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class JaccardCalculationBasedCreatorTest extends AbstractProbCalcBasedVectorCreatorTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays
                .asList(new Object[][] {
                        /*
                         * word1 1 1 1
                         * 
                         * word2 0 1 1
                         * 
                         * word3 0 1 1
                         * 
                         * vector1 1 2/3 2/3
                         * 
                         * vector2 2/3 1 1
                         * 
                         * vector3 2/3 1 1
                         */
                        {
                                3,
                                new double[][] { { 0, 1.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0,
                                        2.0 / 3.0 } },
                                new double[][][] { { { 1, 2.0 / 3.0, 2.0 / 3.0 }, { 2.0 / 3.0, 1, 1 },
                                        { 2.0 / 3.0, 1, 1 } } }, "V_j(1)", 1 },

                        /*
                         * word1 0 1 1
                         * 
                         * word2 1 0 1
                         * 
                         * word3 1 1 0
                         * 
                         * vector1 1 1/3 1/3
                         * 
                         * vector2 1/3 1 1/3
                         * 
                         * vector3 1/3 1/3 1
                         */{
                                3,
                                new double[][] { { 0, 2.0 / 3.0, 2.0 / 3.0, 1.0 / 3.0, 2.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0,
                                        0 } },
                                new double[][][] { { { 1, 1.0 / 3.0, 1.0 / 3.0 }, { 1.0 / 3.0, 1, 1.0 / 3.0 },
                                        { 1.0 / 3.0, 1.0 / 3.0, 1 } } }, "V_j(1)", 1 },
                        /*
                         * word1 0 0 0 1
                         * 
                         * word2 0 1 0 1
                         * 
                         * word3 0 0 1 1
                         * 
                         * vector1 1 1/2 1/2
                         * 
                         * vector2 1/2 1 1/3
                         * 
                         * vector3 1/2 1/3 1
                         */
                        {
                                3,
                                new double[][] { { 0, 0.25, 0.5, 0.25, 0.5, 0.25, 0.25, 0.25 } },
                                new double[][][] { { { 1, 1.0 / 2.0, 1.0 / 2.0 }, { 1.0 / 2.0, 1, 1.0 / 3.0 },
                                        { 1.0 / 2.0, 1.0 / 3.0, 1 } } }, "V_j(1)", 1 },
                        // all together
                        {
                                3,
                                new double[][] {
                                        { 0, 1.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0 },
                                        { 0, 2.0 / 3.0, 2.0 / 3.0, 1.0 / 3.0, 2.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0, 0 },
                                        { 0, 0.25, 0.5, 0.25, 0.5, 0.25, 0.25, 0.25 } },
                                new double[][][] {
                                        { { 1, 2.0 / 3.0, 2.0 / 3.0 }, { 2.0 / 3.0, 1, 1 }, { 2.0 / 3.0, 1, 1 } },
                                        { { 1, 1.0 / 3.0, 1.0 / 3.0 }, { 1.0 / 3.0, 1, 1.0 / 3.0 },
                                                { 1.0 / 3.0, 1.0 / 3.0, 1 } },
                                        { { 1, 1.0 / 2.0, 1.0 / 2.0 }, { 1.0 / 2.0, 1, 1.0 / 3.0 },
                                                { 1.0 / 2.0, 1.0 / 3.0, 1 } } }, "V_j(1)", 1 },
                        /*
                         * word1 1 1 1
                         * 
                         * word2 0 1 1
                         * 
                         * word3 0 1 1
                         * 
                         * vector1 1 2/3 2/3
                         * 
                         * vector2 2/3 1 1
                         * 
                         * vector3 2/3 1 1
                         */
                        {
                                3,
                                new double[][] { { 0, 1.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0,
                                        2.0 / 3.0 } },
                                new double[][][] { { { 1, 4.0 / 9.0, 4.0 / 9.0 }, { 4.0 / 9.0, 1, 1 },
                                        { 4.0 / 9.0, 1, 1 } } }, "V_j(2)", 2 },

                        /*
                         * word1 0 1 1
                         * 
                         * word2 1 0 1
                         * 
                         * word3 1 1 0
                         * 
                         * vector1 1 1/3 1/3
                         * 
                         * vector2 1/3 1 1/3
                         * 
                         * vector3 1/3 1/3 1
                         */{
                                3,
                                new double[][] { { 0, 2.0 / 3.0, 2.0 / 3.0, 1.0 / 3.0, 2.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0,
                                        0 } },
                                new double[][][] { { { 1, 1.0 / 9.0, 1.0 / 9.0 }, { 1.0 / 9.0, 1, 1.0 / 9.0 },
                                        { 1.0 / 9.0, 1.0 / 9.0, 1 } } }, "V_j(2)", 2 },
                        /*
                         * word1 0 0 0 1
                         * 
                         * word2 0 1 0 1
                         * 
                         * word3 0 0 1 1
                         * 
                         * vector1 1 1/2 1/2
                         * 
                         * vector2 1/2 1 1/3
                         * 
                         * vector3 1/2 1/3 1
                         */
                        {
                                3,
                                new double[][] { { 0, 0.25, 0.5, 0.25, 0.5, 0.25, 0.25, 0.25 } },
                                new double[][][] { { { 1, 1.0 / 4.0, 1.0 / 4.0 }, { 1.0 / 4.0, 1, 1.0 / 9.0 },
                                        { 1.0 / 4.0, 1.0 / 9.0, 1 } } }, "V_j(2)", 2 },
                        // all together
                        {
                                3,
                                new double[][] {
                                        { 0, 1.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0 },
                                        { 0, 2.0 / 3.0, 2.0 / 3.0, 1.0 / 3.0, 2.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0, 0 },
                                        { 0, 0.25, 0.5, 0.25, 0.5, 0.25, 0.25, 0.25 } },
                                new double[][][] {
                                        { { 1, 4.0 / 9.0, 4.0 / 9.0 }, { 4.0 / 9.0, 1, 1 }, { 4.0 / 9.0, 1, 1 } },
                                        { { 1, 1.0 / 9.0, 1.0 / 9.0 }, { 1.0 / 9.0, 1, 1.0 / 9.0 },
                                                { 1.0 / 9.0, 1.0 / 9.0, 1 } },
                                        { { 1, 1.0 / 4.0, 1.0 / 4.0 }, { 1.0 / 4.0, 1, 1.0 / 9.0 },
                                                { 1.0 / 4.0, 1.0 / 9.0, 1 } } }, "V_j(2)", 2 } });
    }

    public JaccardCalculationBasedCreatorTest(int wordsetSize, double[][] probabilities, double[][][] expectedVectors,
            String expectedCreatorName, double gamma) {
        super(new JaccardConfirmationMeasure(), wordsetSize, probabilities, expectedVectors, expectedCreatorName,
                gamma);
    }
}
