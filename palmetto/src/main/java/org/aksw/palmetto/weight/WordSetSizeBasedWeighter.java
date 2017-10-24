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
package org.aksw.palmetto.weight;

import org.aksw.palmetto.data.SubsetProbabilities;

@Deprecated
public class WordSetSizeBasedWeighter implements Weighter {

    @Override
    public double[] createWeights(SubsetProbabilities probabilities) {
        // get the number of words the complete word set comprises of
        int numberOfWords = Integer.numberOfTrailingZeros(probabilities.probabilities.length);
        int pos = 0;
        for (int i = 0; i < probabilities.segments.length; ++i) {
            pos += probabilities.conditions[i].length;
        }
        double weights[] = new double[pos];

        pos = 0;
        for (int i = 0; i < probabilities.segments.length; ++i) {
            for (int j = 0; j < probabilities.conditions[i].length; ++j) {
                weights[pos] = ((numberOfWords - Integer.bitCount(probabilities.segments[i]
                        | probabilities.conditions[i][j]))  + 2.0)
                        / numberOfWords;
                ++pos;
            }
        }

        return weights;
    }

    @Override
    public String getName() {
        return "E_l";
    }
}
