package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Test
import kotlin.random.Random

class StatisticsUtilsTest  {

    @Test
    fun `getActiveAndCompletedStats NullTasks returnsZeroAndZero`() {
        // Arrange: Create an empty list of tasks
        val tasks: List<Task>? = null

        // Act: Call the getActiveAndCompletedStats function
        val result = getActiveAndCompletedStats(tasks)

        // Assert: Check that the result returns 0% for both completed and active tasks
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }

    @Test
    fun `getActiveAndCompletedStats emptyTasks returnsZeroAndZero`() {
        // Arrange: Create an empty list of tasks
        val tasks: List<Task> = emptyList()

        // Act: Call the getActiveAndCompletedStats function
        val result = getActiveAndCompletedStats(tasks)

        // Assert: Check that the result returns 0% for both completed and active tasks
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }

    @Test
    fun `getActiveAndCompletedStats noCompleted returnsHundredZero`() {
        // Arrange: Create a list with one active task
        val tasks = listOf(
            Task("title", "desc", isCompleted = false)
        )

        // Act: Call the getActiveAndCompletedStats function
        val result = getActiveAndCompletedStats(tasks)

        // Assert: Check that the result returns 0% completed and 100% active
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(100f))
    }

    @Test
    fun `getActiveAndCompletedStats noActive returnsZeroHundred`() {
        // Arrange: Create a list with one completed task
        val tasks = listOf(
            Task("title", "desc", isCompleted = true)
        )

        // Act: Call the getActiveAndCompletedStats function
        val result = getActiveAndCompletedStats(tasks)

        // Assert: Check that the result returns 100% completed and 0% active
        assertThat(result.completedTasksPercent, `is`(100f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }

    @Test
    fun `getActiveAndCompletedStats oneActiveOneCompleted returnsFiftyFifty`() {
        // Arrange: Create a list with one active and one completed task
        val tasks = listOf(
            Task("title1", "desc1", isCompleted = true),
            Task("title2", "desc2", isCompleted = false)
        )

        // Act: Call the function
        val result = getActiveAndCompletedStats(tasks)

        // Assert: Verify that the percentages are 50% each
        assertThat(result.activeTasksPercent, `is`(50f))
        assertThat(result.completedTasksPercent, `is`(50f))
    }

    @Test
    fun `getActiveAndCompletedStats TwoActiveAndThreeCompleted returnsFortySixty`() {
        // Arrange: Create a list with two active and three completed tasks
        val tasks = listOf(
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = true)
        )

        // Act: Call the getActiveAndCompletedStats function
        val result = getActiveAndCompletedStats(tasks)

        // Assert: Check that the result returns 60% completed and 40% active
        assertThat(result.completedTasksPercent, `is`(60f))
        assertThat(result.activeTasksPercent, `is`(40f))
    }

    @Test
    fun `getActiveAndCompletedStats randomTasksList returnsExpectedPercentages`() {
        // Arrange
        val randomSize = Random.nextInt(1, 100)
        val activeTaskCount = Random.nextInt(0, randomSize)
        val completedTaskCount = randomSize - activeTaskCount

        val tasks = List(randomSize) { index ->
            Task("Task $index", "Description $index", isCompleted = index >= activeTaskCount)
        }

        // Act
        val result = getActiveAndCompletedStats(tasks)

        // Assert
        val expectedActivePercent = (activeTaskCount * 100f) / randomSize
        val expectedCompletedPercent = (completedTaskCount * 100f) / randomSize

        assertThat(result.activeTasksPercent, `is`(expectedActivePercent))
        assertThat(result.completedTasksPercent, `is`(expectedCompletedPercent))
    }
}