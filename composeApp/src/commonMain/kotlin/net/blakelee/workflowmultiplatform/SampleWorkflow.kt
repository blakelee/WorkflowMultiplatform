package net.blakelee.workflowmultiplatform

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.squareup.workflow1.Snapshot
import com.squareup.workflow1.StatefulWorkflow
import com.squareup.workflow1.ui.compose.ComposeScreen

object SampleWorkflow : StatefulWorkflow<Unit, SampleWorkflow.State, Nothing, ComposeScreen>() {

    data class State(val count: Int)

    override fun render(
        renderProps: Unit,
        renderState: State,
        context: RenderContext
    ): ComposeScreen {
        return ComposeScreen {
            Column {
                Text("Count ${renderState.count}")
                Button(
                    onClick = context.eventHandler {
                        state = state.copy(count = state.count + 1)
                    },
                    content = { Text("Increment") }
                )
            }
        }
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): State = State(1)

    override fun snapshotState(state: State): Snapshot? = null
}

