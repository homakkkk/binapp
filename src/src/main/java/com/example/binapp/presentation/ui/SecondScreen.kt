import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.binapp.R
import com.example.binapp.presentation.viewmodels.HistoryViewModel
import com.example.binapp.presentation.states.HistoryItem
@Composable
fun SecondScreen(navController: NavHostController, historyViewModel: HistoryViewModel = viewModel()) {
    val historyItems by historyViewModel.historyItems.collectAsState(emptyList())

    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(historyItems) { item ->
            HistoryCard(item)
        }
    }

    FloatingActionButton(
        onClick = { historyViewModel.clearHistory() },
        modifier = Modifier.padding(bottom = 16.dp, end = 16.dp)
    ) {
        Icon(imageVector = Icons.Default.Delete, contentDescription = stringResource(R.string.delete_history))
    }
}
@Composable
fun HistoryCard(item: HistoryItem) {
    Card(elevation = 4.dp, shape = RoundedCornerShape(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "BIN: ${item.binNumber}", style = MaterialTheme.typography.h6)
            Text(text = "Банк: ${item.bankName}", style = MaterialTheme.typography.body1)
            Text(text = "Страна: ${item.countryName}", style = MaterialTheme.typography.body1)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewSecondScreen() {
    SecondScreen(NavHostController(LocalContext.current))
}