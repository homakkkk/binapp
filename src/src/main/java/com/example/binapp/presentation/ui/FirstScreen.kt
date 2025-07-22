import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.binapp.presentation.states.ScreenState
import com.example.binapp.presentation.viewmodels.MainViewModel

@Composable
fun FirstScreen(navController: NavController, viewModel: MainViewModel) {
    var binInput by remember { mutableStateOf("") }
    val screenState by viewModel.screenState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.height(16.dp))

        TextField(
            value = binInput,
            onValueChange = { newValue ->
                binInput = newValue
            },
            label = { Text("Enter BIN Number") },
            modifier = Modifier.padding(horizontal = 16.dp),
            singleLine = true
        )

        Button(onClick = {
            viewModel.loadCardInfo(binInput)
        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Lookup")
        }

        Spacer(Modifier.height(8.dp))

        ScreenContent(screenState)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(onClick = {
                navController.navigate("history_screen")
            }) {
                Text("Show History")
            }
        }
    }
}

@Composable
private fun ScreenContent(state: ScreenState) {
    when (state) {
        is ScreenState.Loading -> LoadingIndicator()
        is ScreenState.Error -> ErrorMessage(state.error)
        is ScreenState.Success -> SuccessCardDetails(state.cardInfo)
    }
}

@Composable
private fun LoadingIndicator() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorMessage(error: Throwable) {
    Text(text = error.message ?: "Unknown error occurred.")
}

@Composable
private fun SuccessCardDetails(cardInfo: CardInfo) {
    Column {
        Text("Country: ${cardInfo.countryName}", style = MaterialTheme.typography.subtitle1)
        Text("Brand: ${cardInfo.brand}", style = MaterialTheme.typography.subtitle1)
        Text("Bank Phone: ${cardInfo.bankPhone ?: "-"}", style = MaterialTheme.typography.subtitle1)
        Text("Bank Website: ${cardInfo.bankWebsite ?: "-"}", style = MaterialTheme.typypography.subtitle1)
    }
}