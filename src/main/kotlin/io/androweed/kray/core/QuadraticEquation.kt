package io.androweed.kray.core

import kotlin.math.sign
import kotlin.math.sqrt

/**
 * Solves quadratic equations.
 * This class has value semantic, so that is can be considered immutable and thread safe.
 *
 * Note that for performance matters the result of some intermediate computations can be cached.
 */
class QuadraticEquation(private val a: Double, private val b: Double, private val c: Double) {
    fun hasNoRoot() = a != 0.0 && discriminant < 0.0
    fun hasOneRoot() =  a == 0.0 || discriminant == 0.0
    fun hasTwoRoot() =  a != 0.0 && discriminant > 0.0

    private val discriminant by lazy {
        b * b - 4 * a * c
    }

    private val solutions by lazy {
        val rootSquaredDelta = sqrt(discriminant)

        /*
         *  The numerically computed solution would be  :
         *   double root1 = (-b - rootSquaredDelta)/denominator;
         *   double root2 = (-b + rootSquaredDelta)/denominator;
         *
         *  But to avoid cancellation issues in case b*b is much greater that 4*a*c
         *  we compute roots in an other way.
         *
         *  For more details about cancellation see : https://en.wikipedia.org/wiki/Loss_of_significance
         */
        val root1 = (-b - sign(b) * rootSquaredDelta) / (2 * a)
        val root2 = c / (a * root1)

        if (root1 < root2) {
            Pair(root1, root2)
        } else {
            Pair(root2, root1)
        }
    }

    fun firstRoot(): Double {
        if (a == 0.0) {
            return -b / c
        }

        if (discriminant < 0.0) {
            return Double.NaN
        }

        if (discriminant ==  0.0) {
            return -b / (2 * a)
        }

        return solutions.first
    }

    fun secondRoot(): Double {
        if (a == 0.0) {
            return -b / c
        }

        if (discriminant < 0.0) {
            return Double.NaN
        }

        if (discriminant ==  0.0) {
            return -b / (2 * a)
        }

        return solutions.second
    }
}
