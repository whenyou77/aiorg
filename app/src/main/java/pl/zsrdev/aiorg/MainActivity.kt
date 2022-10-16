package pl.zsrdev.aiorg

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.children
import pl.zsrdev.aiorg.screen.Screen
import pl.zsrdev.aiorg.screen.SetupMethod
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var ash: Ash

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen)

        ash = Ash(this)

//        val json = "{\"priorities\":[1,5,10,2,4,6,8,8,1,9,7,4,4,5,8,3],\"exams\":[{\"date\":\"2022-09-30\",\"subject\":0,\"description\":\"ae\",\"weight\":5}],\"subjects\":[\"Geografia\",\"Język polski\",\"Historia\",\"Język angielski\",\"Muzyka\",\"Technika\",\"Religia\",\"Chemia\",\"Matematyka\",\"Wiedza o społeczeństwie\",\"Fizyka\",\"Wychowanie fizyczne\",\"Biologia\",\"Zajęcia z wychowawcą\",\"Język niemiecki\",\"Informatyka\"],\"grades\":[{\"subject\":0,\"weight\":5,\"description\":\"test\",\"value\":1},{\"subject\":12,\"weight\":5,\"description\":\"i chuj\",\"value\":2},{\"subject\":12,\"weight\":3,\"description\":\"i chuj x2\",\"value\":1},{\"subject\":2,\"weight\":3,\"description\":\"a tu git\",\"value\":6}],\"timetable\":[[0,1,2,3,3,4,5],[6,7,8,9,10],[8,3,1,1,8,11],[12,2,13,14,11,11],[1,12,8,12,7,0,15]]}"
//        updateManual(ash.userData, json)

//        ash.showScreen(ash.home)

//        val json = "{\"priorities\":[1,5,10,2,4,6,8,8,1,9,7,4,4,5,8,3],\"exams\":[{\"date\":\"2022-09-30\",\"subject\":0,\"description\":\"ae\",\"weight\":5}],\"subjects\":[\"Geografia\",\"Język polski\",\"Historia\",\"Język angielski\",\"Muzyka\",\"Technika\",\"Religia\",\"Chemia\",\"Matematyka\",\"Wiedza o społeczeństwie\",\"Fizyka\",\"Wychowanie fizyczne\",\"Biologia\",\"Zajęcia z wychowawcą\",\"Język niemiecki\",\"Informatyka\"],\"grades\":[{\"subject\":0,\"weight\":5,\"description\":\"test\",\"value\":1},{\"subject\":12,\"weight\":5,\"description\":\"i chuj\",\"value\":2},{\"subject\":12,\"weight\":3,\"description\":\"i chuj x2\",\"value\":1},{\"subject\":2,\"weight\":3,\"description\":\"a tu git\",\"value\":6}],\"timetable\":[[0,1,2,3,3,4,5],[6,7,8,9,10],[8,3,1,1,8,11],[12,2,13,14,11,11],[1,12,8,12,7,0,15]]}"
//        userData.fromJsonString(json)
//        println(userData.createSchedule())

//        var state: State = State.LOGIN;

//        setupScreen = SetupScreen(this, ash.userData)

//        ash.manualFile.delete()

        if (ash.configFile.exists())
        {
            updateConfig(ash.configData, ash.configFile.readText())

            when (ash.configData.method) {
                Method.MANUAL -> {
                    if (ash.manualFile.exists()) {
                        updateManual(ash.userData, ash.manualFile.readText())
                        ash.showScreen(ash.home)
                    } else {
                        ash.showScreen(ash.setupMethod)
                    }
                }

                Method.VULCAN -> {
                    ash.showScreen(ash.home)
                }
            }
        }
        else
        {
            ash.showScreen(ash.setupMethod)
        }
    }
}