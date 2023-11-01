import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import me.time.SpringMain

fun main() = application {
    SpringMain.initialize()
    Window(onCloseRequest = {
        SpringMain.shutdown()
        ::exitApplication.invoke()
    }) {
        App()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}