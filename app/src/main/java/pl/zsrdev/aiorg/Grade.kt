package pl.zsrdev.aiorg

class Grade(val subject: Int, val value: Double, val weight: Int, val description: String) {
    override fun toString(): String {
        return "(subject: $subject, value: $value, weight: $weight, description: $description)"
    }
}