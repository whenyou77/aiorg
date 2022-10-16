import pl.zsrdev.aiorg.UserData

fun main(args: Array<String>) {
    val userData = UserData()
//    userData.subjects.add("Geografia")
//    userData.subjects.add("Język polski")
//    userData.subjects.add("Historia")
//    userData.subjects.add("Język angielski")
//    userData.subjects.add("Muzyka")
//    userData.subjects.add("Technika")
//    userData.subjects.add("Religia")
//    userData.subjects.add("Chemia")
//    userData.subjects.add("Matematyka")
//    userData.subjects.add("Wiedza o społeczeństwie")
//    userData.subjects.add("Fizyka")
//    userData.subjects.add("Wychowanie fizyczne")
//    userData.subjects.add("Biologia")
//    userData.subjects.add("Zajęcia z wychowawcą")
//    userData.subjects.add("Język niemiecki")
//    userData.subjects.add("Informatyka")
//
//
//
//
//    userData.priorities.add(1)
//    userData.priorities.add(5)
//    userData.priorities.add(10)
//    userData.priorities.add(2)
//    userData.priorities.add(4)
//    userData.priorities.add(6)
//    userData.priorities.add(8)
//    userData.priorities.add(8)
//    userData.priorities.add(1)
//    userData.priorities.add(9)
//    userData.priorities.add(7)
//    userData.priorities.add(4)
//    userData.priorities.add(4)
//    userData.priorities.add(5)
//    userData.priorities.add(8)
//    userData.priorities.add(3)
//
//    userData.timetable[0].add(0)
//    userData.timetable[0].add(1)
//    userData.timetable[0].add(2)
//    userData.timetable[0].add(3)
//    userData.timetable[0].add(3)
//    userData.timetable[0].add(4)
//    userData.timetable[0].add(5)
//
//    userData.timetable[1].add(6)
//    userData.timetable[1].add(7)
//    userData.timetable[1].add(8)
//    userData.timetable[1].add(9)
//    userData.timetable[1].add(10)
//
//    userData.timetable[2].add(8)
//    userData.timetable[2].add(3)
//    userData.timetable[2].add(1)
//    userData.timetable[2].add(1)
//    userData.timetable[2].add(8)
//    userData.timetable[2].add(11)
//
//    userData.timetable[3].add(12)
//    userData.timetable[3].add(2)
//    userData.timetable[3].add(13)
//    userData.timetable[3].add(14)
//    userData.timetable[3].add(11)
//    userData.timetable[3].add(11)
//
//    userData.timetable[4].add(1)
//    userData.timetable[4].add(12)
//    userData.timetable[4].add(8)
//    userData.timetable[4].add(12)
//    userData.timetable[4].add(7)
//    userData.timetable[4].add(0)
//    userData.timetable[4].add(15)
//
//    userData.grades.add(Grade(userData.subjects.indexOf("Geografia"), 1.0, 5, "test"))
//    userData.grades.add(Grade(userData.subjects.indexOf("Biologia"), 2.0, 5, "i chuj"))
//    userData.grades.add(Grade(userData.subjects.indexOf("Biologia"), 1.0, 3, "i chuj x2"))
//    userData.grades.add(Grade(userData.subjects.indexOf("Historia"), 6.0, 3, "a tu git"))
//
//    userData.exams.add(Exam(userData.subjects.indexOf("Geografia"), 5, LocalDate.now().plusDays(1), "ae"))

//    val dayOfWeek = 1;
//    val timetablePriorities = arrayOf(10.0, 5.0, 3.0, 2.0, 1.0)
//    val gradePriorities = arrayOf(20.0, 15.0, 10.0, 5.0, -5.0, -10.0)
//    val examPriorities = arrayOf(10.0, 1.0, 5.0, 2.0, 1.0)
//
//    val timetable = Array(userData.subjects.size) { 0.0 }
//    userData.timetable.forEachIndexed { index, ints ->
//        ints.forEach {
//            timetable[it] = max(timetable[it], timetablePriorities[abs(index - dayOfWeek) % 5])
//        }
//    }
//
//    val grades = Array(userData.subjects.size) { 0.0 }
//    userData.subjects.forEachIndexed { index, s ->
//        val average = userData.calcAverageFor(s)
//
//        if (average == 0.0) {
//            grades[index] = 0.0
//        } else {
//            val avgInt = max(1, min(6, average.roundToInt()))
//            grades[index] = gradePriorities[avgInt - 1]
//        }
//    }
//
//    val exams = Array(userData.subjects.size) { 0.0 }
//    userData.subjects.forEachIndexed { index, s ->
//        val exam = userData.getFirstExam(s)
//
//        if (exam != null) {
//            val daysBefore = exam.date.minusDays(LocalDate.now().toEpochDay()).toEpochDay()
//
//            if (daysBefore in 1..5)
//                exams[index] = examPriorities[daysBefore.toInt() - 1] * exam.weight
//
//            // exams[index] = examPriorities[day]
//
//        }
//    }
//
//    val final = Array(userData.subjects.size) { 0.0 }
//    final.forEachIndexed { index, i ->
//        final[index] = timetable[index] + grades[index] + exams[index] + userData.priorities[index]
//    }
//
//
//
//    println(userData.priorities.toString())
//    println(timetable.contentToString())
//    println(grades.contentToString())
//    println(exams.contentToString())
//    println()
//    println(final.contentToString())

//    val json = "{\"priorities\":[1,5,10,2,4,6,8,8,1,9,7,4,4,5,8,3],\"exams\":[{\"date\":\"2022-09-28\",\"subject\":0,\"description\":\"ae\",\"weight\":5}],\"subjects\":[\"Geografia\",\"Język polski\",\"Historia\",\"Język angielski\",\"Muzyka\",\"Technika\",\"Religia\",\"Chemia\",\"Matematyka\",\"Wiedza o społeczeństwie\",\"Fizyka\",\"Wychowanie fizyczne\",\"Biologia\",\"Zajęcia z wychowawcą\",\"Język niemiecki\",\"Informatyka\"],\"grades\":[{\"subject\":0,\"weight\":5,\"description\":\"test\",\"value\":1},{\"subject\":12,\"weight\":5,\"description\":\"i chuj\",\"value\":2},{\"subject\":12,\"weight\":3,\"description\":\"i chuj x2\",\"value\":1},{\"subject\":2,\"weight\":3,\"description\":\"a tu git\",\"value\":6}],\"timetable\":[[0,1,2,3,3,4,5],[6,7,8,9,10],[8,3,1,1,8,11],[12,2,13,14,11,11],[1,12,8,12,7,0,15]]}"
//    userData.fromJsonString(json)
//    println(userData.createSchedule())





//    runBlocking {
//        val sdk = Sdk()
//
//        sdk.apply {
//            mode = Sdk.Mode.SCRAPPER
//            loginType = Sdk.ScrapperLoginType.STANDARD
//        }
//
//        var students = sdk.getStudentsFromScrapper(
//            EMAIL, PASSWORD, "https://vulcan.net.pl", "powiatpoznanski"
//        )
//
//        // print(students[0])
//
//        sdk.apply {
//            classId = students[0].classId
//            studentId = students[0].studentId
//            schoolSymbol = students[0].schoolSymbol
//        }
//
//        students[0].semesters.forEach {
//            val now = LocalDate.now()
//            if (it.start.isBefore(now) && it.end.isAfter(now)) {
//                // print(it)
//
//                sdk.apply {
//                    diaryId = it.diaryId
//                }
//
//                // print(sdk.getGrades(it.semesterId))
//            }
//        }
//
//        var nowDate = LocalDate.now()
//        var startDate = nowDate.minusDays(nowDate.dayOfWeek.value.toLong() - 1)
//        var endDate = startDate.plusDays(5)
//
//        var timetableFull = sdk.getTimetableFull(startDate, endDate)
//
//        timetableFull.lessons.forEach {
//            if (!userData.subjects.contains(it.subject))
//                userData.subjects.add(it.subject)
//
//            userData.timetable[it.date.dayOfWeek.value - 1].add(
//                userData.subjects.indexOf(it.subject)
//            )
//        }
//
//        var grades = sdk.getGrades(students[0].semesters[0].semesterId)
//
//        grades.first.forEach {
//            userData.grades.add(Grade(
//                it.subject, it.value, it.weightValue, it.description
//            ))
//        }
//
//        var exams = sdk.getExams(nowDate, nowDate.plusDays(5), students[0].semesters[0].semesterId)
//
//        exams.forEach {
//            userData.exams.add(Exam(
//                it.subject, ExamType.TEST, it.date, it.description
//            ))
//        }
//
//        // print(students[0].semesters)
//        println(userData.grades)
//        println(userData.subjects)
//        println(userData.exams)
//        println(userData.toJsonString())
//    }
//
//
}