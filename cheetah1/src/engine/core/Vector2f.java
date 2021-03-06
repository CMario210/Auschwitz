/*
 * Copyright 2017 Carlos Rodriguez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package engine.core;

/**
*
* @author Carlos Rodriguez
* @version 1.0
* @since 2017
*/
public class Vector2f {

    private float x;
    private float y;

    /**
     * Constructor of the float vector.
     * @param x data.
     * @param y data.
     */
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the length of the vector.
     * @return Vector's length.
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }
    
    /**
     * Gets the maximum between two numbers.
     * @return The maximum number.
     */
    public float max() { return Math.max(x, y); }

    /**
     * Dot vector.
     * @param r vector
     * @return Vector
     */
    public float dot(Vector2f r) {
        return x * r.getX() + y * r.getY();
    }

    /**
     * Cross vector.
     * @param r vector
     * @return Vector
     */
    public float cross(Vector2f r) {
        return x * r.getY() - y * r.getX();
    }

    /**
     * Normalizes the data in vector.
     * @return Vector's data normalized.
     */
    public Vector2f normalized() {
        float length = length();

        return new Vector2f(x / length, y / length);
    }

    /**
     * Rotates the vector's data by an float angle
     * @param angle Rotating angle.
     * @return Vector's data rotated.
     */
    public Vector2f rotate(float angle) {
        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        return new Vector2f((float) (x * cos - y * sin), (float) (x * sin + y * cos));
    }

    /**
     * Adds the data of one new vector to the main vector.
     * @param r Vector data to join.
     * @return Vector with more data.
     */
    public Vector2f add(Vector2f r) {
        return new Vector2f(x + r.getX(), y + r.getY());
    }

    /**
     * Adds float data to the vector.
     * @param r Vector's new data.
     * @return Vector with more data.
     */
    public Vector2f add(float r) {
        return new Vector2f(x + r, y + r);
    }

    /**
     * Subtracts the vector's data minus r float vector data.
     * @param r Vector to subtract.
     * @return Vector subtracted.
     */
    public Vector2f sub(Vector2f r) {
        return new Vector2f(x - r.getX(), y - r.getY());
    }

    /**
     * Subtracts the vector's data minus r float number.
     * @param r Number to subtract.
     * @return Vector subtracted.
     */
    public Vector2f sub(float r) {
        return new Vector2f(x - r, y - r);
    }

    /**
     * Multiplies the vector by r float vector.
     * @param r Vector to multiply.
     * @return Vector multiplied.
     */
    public Vector2f mul(Vector2f r) {
        return new Vector2f(x * r.getX(), y * r.getY());
    }

    /**
     * Multiplies the vector by r float number.
     * @param r Number to multiply.
     * @return Vector multiplied.
     */
    public Vector2f mul(float r) {
        return new Vector2f(x * r, y * r);
    }

    /**
     * Divides the vector between r float vector number.
     * @param r Number to divide.
     * @return Vector divided.
     */
    public Vector2f div(Vector2f r) {
        return new Vector2f(x / r.getX(), y / r.getY());
    }

    /**
     * Divides the vector between r float number.
     * @param r Number to divide.
     * @return Vector divided.
     */
    public Vector2f div(float r) {
        return new Vector2f(x / r, y / r);
    }

    /**
     * Gets the absolute value of the vector's data.
     * @return Absolute value of the vector's data.
     */
    public Vector2f abs() {
        return new Vector2f(Math.abs(x), Math.abs(y));
    }

    /**
     * Leaps the vector into an new vector depending on the amount
     * of size the vector adds data.
     * @param newVector The new vector.
     * @param amt Amount of multiplication for the new vector.
     * @return Leaped new vector.
     */
    public Vector2f lerp(Vector2f newVector, float amt) {
        return this.sub(newVector).mul(amt).add(newVector);
    }

    /**
     * Converts the vector's data to string.
     * @return Data in string.
     */
    public String toString() {
        return "(" + x + " " + y + ")";
    }

    /**
     * Gets the vectors data and compares to other vector.
     * @param r Vector to compare.
     * @return A boolean state.
     */
    public boolean equals(Vector2f r) {
        return r.getX() == x && r.getY() == y;
    }

    /**
     * Gets the x value in vector.
     * @return X's value.
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the data in x part of the vector.
     * @param x Data to the vector.
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Gets the y value in vector.
     * @return Y's value.
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the data in y part of the vector.
     * @param y Data to the vector.
     */
    public void setY(float y) {
        this.y = y;
    }
}
