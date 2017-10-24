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
package org.aksw.palmetto.calculations.direct;

import org.aksw.palmetto.data.SubsetProbabilities;

/**
 * This confirmation measure calculates the difference of the conditional
 * probability of W' given W* and the conditional probability of W' given ¬W*.
 * The difference is normalized by the sum of these two probabilities. result =
 * (P(W'|W*)-P(W'|¬W*))/(P(W'|W*)+P(W'|¬W*))
 * 
 * @author Michael Röder
 * 
 */
public class FitelsonConfirmationMeasure extends AbstractUndefinedResultHandlingConfirmationMeasure {

    public FitelsonConfirmationMeasure() {
        super();
    }

    public FitelsonConfirmationMeasure(double resultIfCalcUndefined) {
        super(resultIfCalcUndefined);
    }

    @Override
    public double[] calculateConfirmationValues(SubsetProbabilities subsetProbabilities) {
        int pos = 0;
        for (int i = 0; i < subsetProbabilities.segments.length; ++i) {
            pos += subsetProbabilities.conditions[i].length;
        }
        double values[] = new double[pos];

        double segmentProbability, intersectionProbability, conditionProbability, conditionalProbability, otherCondProb;
        pos = 0;
        for (int i = 0; i < subsetProbabilities.segments.length; ++i) {
            segmentProbability = subsetProbabilities.probabilities[subsetProbabilities.segments[i]];
            for (int j = 0; j < subsetProbabilities.conditions[i].length; ++j) {
                conditionProbability = subsetProbabilities.probabilities[subsetProbabilities.conditions[i][j]];
                intersectionProbability = subsetProbabilities.probabilities[subsetProbabilities.segments[i]
                        | subsetProbabilities.conditions[i][j]];
                if (conditionProbability > 0) {
                    conditionalProbability = intersectionProbability
                            / subsetProbabilities.probabilities[subsetProbabilities.conditions[i][j]];
                } else {
                    conditionalProbability = 0;
                }
                if (conditionProbability < 1) {
                    otherCondProb = (segmentProbability - intersectionProbability) / (1 - conditionProbability);
                } else {
                    otherCondProb = 0;
                }
                if ((conditionalProbability > 0) || (otherCondProb > 0)) {
                    values[pos] = (conditionalProbability - otherCondProb) / (conditionalProbability + otherCondProb);
                } else {
                    values[pos] = resultIfCalcUndefined;
                }
                ++pos;
            }
        }
        return values;
    }

    @Override
    public String getName() {
        return "m_f";
    }
}
