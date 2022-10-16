package pl.zsrdev.aiorg

enum class Method {
    MANUAL,
    VULCAN
}

class ConfigData {
    var method = Method.MANUAL
    var hoursPerDay = 2
    var priorities: Array<Int> = Array(0) { 0 }
}