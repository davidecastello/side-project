package io.moku.davide.sideproject.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import io.moku.davide.sideproject.R
import io.moku.davide.sideproject.R.layout.activity_kotlin
import io.moku.davide.sideproject.model.User
import io.moku.davide.sideproject.utils.activity.BasicSecondaryActivity
import kotlinx.android.synthetic.main.activity_kotlin.*
import java.util.*
import kotlin.collections.HashMap


/**
 * Intent to start this Activity
 *
 * - Java usage:
 *      startActivity(KotlinTestActivityKt.KotlinActivityIntent(this, getString(R.string.test_message)));
 * - Kotlin usage:
 *      startActivity(KotlinActivityIntent(getString(R.string.test_message)))
 */
fun Context.KotlinActivityIntent(msg: String?): Intent {
    return Intent(this, KotlinTestActivity::class.java).apply {
        putExtra(KotlinTestActivity.EXTRA_MSG, msg)
    }
}

class KotlinTestActivity : BasicSecondaryActivity() {

    /* Static Fields */
    companion object {
        const val EXTRA_MSG = "extra_msg"
        @JvmField val TAG : String? = KotlinTestActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_kotlin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /* Test new functions */
        val myFavoriteNumber : Int = 9
        var currentNumber = myFavoriteNumber.triple()
        currentNumber = currentNumber.double()

        /* Formatting String with variables */
        log(msg = "myFavoriteNumber: $myFavoriteNumber")
        log(msg = "currentNumber: $currentNumber")

        /* Retrieve Intent */
        message.text = intent.getStringExtra(EXTRA_MSG) ?: getString(R.string.no_message_yet)
        button.setOnClickListener { startActivity(KotlinActivityIntent(getString(R.string.test_message))) }

        /* Test default and named arguments */
        val davide = Developer(name = "Davide Castello", age = 23, yearsOfExperience = 2, profile = "Android Developer");
        log(davide.toString())

        /* When */
        val grade = when(davide.yearsOfExperience) {
            /* Single values */
            0,1 -> "Junior"
            /* Interval */
            in 2..10 -> "Senior"
            else -> "Gucci"
        }
        log("Grade: $grade")

        /* Predicates */
        log(msg = "Users whose name starts with 's': ${User.getAllUsers().count { user -> user.name.startsWith("S") }.toString()}")
        log(msg = containsEven(listOf(1, 2, 3)).toString())

        /* Functional programming */
        listOf(1, 2, 3, 4)                          // (1, 2, 3, 4)
                .map { it * 10 }                    // (10, 20, 30, 40)
                .filter { it > 20 }                 // (30, 40)
                .forEach { log(it.toString()) }     // prints 30, 40

    }

    /* Adding functions to existing classes */
    fun Int.triple() = this * 3
    fun Int.double() = this * 2

    fun log(msg : String) = Log.i(TAG, msg)

    class Developer(val name: String,
                    val age: Int,
                    val yearsOfExperience: Int = 0,
                    val profile: String = "") {

        /* Printing directly on more lines*/
        override fun toString(): String = """
                |${::name.name}: $name
                |${::age.name}: $age
                |${::yearsOfExperience.name}: $yearsOfExperience
                |${::profile.name}: $profile
                """.trimMargin()
    }


    /* Java Conversion to Kotlin */
    fun toJSON(collection: Collection<Int>) : String {
        var json = "["
        for (item in collection) {
            json = json.plus(item.toString())
            if (collection.indexOf(item) != (collection.size - 1)) {
                json = json.plus(", ")
            }
        }
        return json.plus("]")
    }

    fun containsEven(collection: Collection<Int>): Boolean = collection.any { item -> (item % 2) == 0 }

    /* Override compare method in a custom class */
    data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

        override fun compareTo(other: MyDate): Int {
            if ((this.year < other.year) or (this.month < other.month) or (this.dayOfMonth < other.dayOfMonth)) return -1
            else return 1
        }

        fun toMillis(): Long {
            val c = Calendar.getInstance()
            c.set(year, month, dayOfMonth, 0, 0, 0)
            c.set(Calendar.MILLISECOND, 0)
            return c.getTimeInMillis()
        }
    }

    /* Fold test */
    fun test() {
        listOf(1, 3, -4, 2, -11).partition { it > 0 }
        val list = listOf(1, 2, 3, 4)
        list.fold(list.first(), operation = {
            partProduct, element ->
            element * partProduct
        }).toString()
    }

    /* Parameter of type extension function */
    fun buildMap(build: HashMap<Int, String>.() -> Unit): HashMap<Int, String> {
        val map = HashMap<Int, String>()
        map.build()
        return map
    }
    fun usage(): Map<Int, String> {
        return buildMap {
            put(0, "0")
            for (i in 1..10) {
                put(i, "$i")
            }
        }
    }












}
