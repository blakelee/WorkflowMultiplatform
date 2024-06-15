package net.blakelee.workflowmultiplatform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.squareup.workflow1.ui.ViewEnvironment
import com.squareup.workflow1.ui.compose.WorkflowRendering
import com.squareup.workflow1.ui.compose.renderAsState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val rendering by SampleWorkflow.renderAsState(Unit) {}

    WorkflowRendering(rendering, ViewEnvironment.EMPTY)
}