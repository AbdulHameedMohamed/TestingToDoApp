package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.utils.getOrAwaitValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TasksViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var tasksRepository: FakeTestRepository
    private lateinit var tasksViewModel: TasksViewModel

    @Before
    fun setupViewModel() {
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)

        tasksViewModel = TasksViewModel(tasksRepository)
    }

    @Test
    fun addNewTask_setsNewTaskEvent() {

        // When adding a new task
        tasksViewModel.addNewTask()

        // Then the new task event is triggered
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled(), not(nullValue()))
    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {

        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the "Add task" action is visible
        val value = tasksViewModel.tasksAddViewVisible.getOrAwaitValue()
        assertThat(value, `is`(true))
    }

    @Test
    fun setFilterAllTasks_currentFilteringLabel_AllTasks() {

        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the currentFilteringLabel To AllTasks
        val value = tasksViewModel.currentFilteringLabel.getOrAwaitValue()
        assertThat(value, `is`(R.string.label_all))
    }

    @Test
    fun setFilterAllTasks_noTasksLabel_NoTasksAll() {

        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the currentFilteringLabel To AllTasks
        val value = tasksViewModel.noTasksLabel.getOrAwaitValue()
        assertThat(value, `is`(R.string.no_tasks_all))
    }

    @Test
    fun setFilterAllTasks_noTaskIconRes_LogoNoFill() {

        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the currentFilteringLabel To AllTasks
        val value = tasksViewModel.noTaskIconRes.getOrAwaitValue()
        assertThat(value, `is`(R.drawable.logo_no_fill))
    }

    @Test
    fun setFilterActiveTasks_tasksAddViewGone() {

        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ACTIVE_TASKS)

        // Then the "Add task" action is visible
        val value = tasksViewModel.tasksAddViewVisible.getOrAwaitValue()
        assertThat(value, `is`(false))
    }

    @Test
    fun setFilterActiveTasks_currentFilteringLabel_ActiveTasks() {

        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ACTIVE_TASKS)

        // Then the currentFilteringLabel To AllTasks
        val value = tasksViewModel.currentFilteringLabel.getOrAwaitValue()
        assertThat(value, `is`(R.string.label_active))
    }

    @Test
    fun setFilterActiveTasks_noTasksLabel_NoTasksActive() {

        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ACTIVE_TASKS)

        // Then the currentFilteringLabel To AllTasks
        val value = tasksViewModel.noTasksLabel.getOrAwaitValue()
        assertThat(value, `is`(R.string.no_tasks_active))
    }

    @Test
    fun setFilterActiveTasks_noTaskIconRes_LogoNoFill() {

        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ACTIVE_TASKS)

        // Then the currentFilteringLabel To AllTasks
        val value = tasksViewModel.noTaskIconRes.getOrAwaitValue()
        assertThat(value, `is`(R.drawable.ic_check_circle_96dp))
    }

    @Test
    fun setFilterCompletedTasks_tasksAddViewGone() {

        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.COMPLETED_TASKS)

        // Then the "Add task" action is visible
        val value = tasksViewModel.tasksAddViewVisible.getOrAwaitValue()
        assertThat(value, `is`(false))
    }

    @Test
    fun setFilterCompletedTasks_currentFilteringLabel_CompletedTasks() {

        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.COMPLETED_TASKS)

        // Then the currentFilteringLabel To AllTasks
        val value = tasksViewModel.currentFilteringLabel.getOrAwaitValue()
        assertThat(value, `is`(R.string.label_completed))
    }

    @Test
    fun setFilterCompletedTasks_noTasksLabel_NoTasksCompleted() {

        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.COMPLETED_TASKS)

        // Then the currentFilteringLabel To AllTasks
        val value = tasksViewModel.noTasksLabel.getOrAwaitValue()
        assertThat(value, `is`(R.string.no_tasks_completed))
    }

    @Test
    fun setFilterCompletedTasks_noTaskIconRes_LogoNoFill() {

        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.COMPLETED_TASKS)

        // Then the currentFilteringLabel To AllTasks
        val value = tasksViewModel.noTaskIconRes.getOrAwaitValue()
        assertThat(value, `is`(R.drawable.ic_verified_user_96dp))
    }
}