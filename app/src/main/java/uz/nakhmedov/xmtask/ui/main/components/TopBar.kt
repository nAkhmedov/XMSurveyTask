package uz.nakhmedov.xmtask.ui.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uz.nakhmedov.xmtask.R

@Composable
fun TopBar(
    isPreviousButtonEnabled: Boolean,
    isNextButtonEnabled: Boolean,
    onBackClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back"
            )
        }
        Text(
            modifier = Modifier.weight(2f),
            text = "Title"
        )
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = onPreviousClick,
            enabled = isPreviousButtonEnabled
        ) {
            Text(text = stringResource(id = R.string.previous))
        }
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = onNextClick,
            enabled = isNextButtonEnabled
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}