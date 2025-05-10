package org.example.project.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.Components.FieldWithDropdown
import org.example.project.Components.FieldWithLabel
import org.example.project.ViewModel.TimerViewModel
import org.example.project.ViewModel.UserViewModel

class ForSponsorScreen(private val timerViewModel: TimerViewModel) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = UserViewModel()
        val fonds = viewModel.getFond()
        val runners = viewModel.getUsers()

        Scaffold(
            topBar = { ForSponsorTopBar() },
            bottomBar = { ForSposnorBottomBar(timerViewModel = timerViewModel) }
        ) { paddingValues ->

            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(horizontal = 100.dp)
                ) {
                    val fontSizeFirst = (maxWidth.value / 30).sp
                    val fontSizeSecond = (maxWidth.value / 40).sp
                    val fontSizeThird = (maxWidth.value / 50).sp
                    val fontSizeFourth = (maxWidth.value / 10).sp
                    val labelWidth = 0.5f
                    var fondsNames = fonds.map { it.name }
                    var runnersNames = runners.map { it.name }
                    var selectedFondName by remember { mutableStateOf("") }
                    var fio by remember { mutableStateOf("") }
                    var runner by remember { mutableStateOf("") }
                    var cardName by remember { mutableStateOf("") }
                    var cardNum by remember { mutableStateOf("") }
                    var srokM by remember { mutableStateOf("") }
                    var srokY by remember { mutableStateOf("") }
                    var cvc by remember { mutableStateOf("") }
                    var sumChangStr by remember { mutableStateOf("10")}
                    var sumChang by remember { mutableStateOf(sumChangStr.toInt()) }
                    var sumSpons by remember { mutableStateOf(0)}

                    Column(
                        modifier = Modifier
                            .fillMaxWidth().padding(bottom = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Спонсор бегуна",
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center,
                            fontSize = fontSizeFirst,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.weight(0.1f))

                        Text(
                            text = "Пожалуйста выберите бегуна, которого вы хотели бы спонсировать и сумму, которую вы хотели бы спонсировать. Спасибо за вашу поддержку бегунов и их благотворительных учреждений.",
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center,
                            fontSize = fontSizeSecond,
                            modifier = Modifier.fillMaxWidth(),
                        )

                        Spacer(Modifier.weight(0.2f))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Column(modifier = Modifier.weight(1f),) {
                                Spacer(modifier = Modifier.weight(0.1f))

                                Box(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "Информация о спонсоре",
                                        color = Color.LightGray,
                                        textAlign = TextAlign.Center,
                                        fontSize = fontSizeFirst,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }

                                Spacer(modifier = Modifier.weight(0.1f))

                                FieldWithLabel(
                                    label = "Ваше имя:",
                                    value = fio,
                                    onValueChange = { fio = it },
                                    placeholder = "Ваше имя",
                                    fontSize = fontSizeSecond,
                                    labelWidth = labelWidth,
                                )

                                Spacer(modifier = Modifier.weight(0.1f))

                                FieldWithDropdown(
                                    label = "Бегун:",
                                    options = runnersNames,
                                    selectedOption = runner,
                                    onOptionSelected = { runner = it },
                                    fontSize = fontSizeSecond,
                                    labelWidth = labelWidth
                                )

                                Spacer(modifier = Modifier.weight(0.1f))

                                FieldWithLabel(
                                    label = "Карта:",
                                    value = cardName,
                                    onValueChange = { cardName = it },
                                    placeholder = "Владелец карты",
                                    fontSize = fontSizeSecond,
                                    labelWidth = labelWidth,
                                )

                                Spacer(modifier = Modifier.weight(0.1f))

                                FieldWithLabel(
                                    label = "Номер карты#:",
                                    value = cardNum,
                                    onValueChange = { cardNum = it },
                                    placeholder = "...",
                                    fontSize = fontSizeSecond,
                                    labelWidth = labelWidth,
                                )

                                Spacer(modifier = Modifier.weight(0.1f))

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Срок действия:",
                                        fontSize = fontSizeSecond,
                                        color = Color.Black,
                                        modifier = Modifier.weight(labelWidth)
                                    )

                                    OutlinedTextField(
                                        value = srokM,
                                        onValueChange = { srokM = it },
                                        placeholder = { Text("MM", fontSize = fontSizeSecond) },
                                        textStyle = androidx.compose.ui.text.TextStyle(
                                            fontSize = fontSizeSecond,
                                            color = Color.Black
                                        ),
                                        modifier = Modifier
                                            .weight((1f - labelWidth) / 2)
                                            .padding(end = 4.dp),
                                        singleLine = true
                                    )

                                    OutlinedTextField(
                                        value = srokY,
                                        onValueChange = { srokY = it },
                                        placeholder = { Text("YY", fontSize = fontSizeSecond) },
                                        textStyle = androidx.compose.ui.text.TextStyle(
                                            fontSize = fontSizeSecond,
                                            color = Color.Black
                                        ),
                                        modifier = Modifier
                                            .weight((1f - labelWidth) / 2)
                                            .padding(start = 4.dp),
                                        singleLine = true
                                    )
                                }

                                Spacer(modifier = Modifier.weight(0.1f))

                                FieldWithLabel(
                                    label = "CVC:",
                                    value = cvc,
                                    onValueChange = { cvc = it },
                                    placeholder = "...",
                                    fontSize = fontSizeSecond,
                                    labelWidth = labelWidth,
                                )
                            }

                            Column(modifier = Modifier.weight(1f),) {
                                Spacer(modifier = Modifier.weight(0.1f))

                                Box(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "Благотворительность",
                                        color = Color.LightGray,
                                        textAlign = TextAlign.Center,
                                        fontSize = fontSizeFirst,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }

                                Spacer(modifier = Modifier.weight(0.1f))

                                FieldWithDropdown(
                                    label = "",
                                    options = fondsNames,
                                    selectedOption = selectedFondName,
                                    onOptionSelected = { selectedFondName = it },
                                    fontSize = fontSizeSecond,
                                    labelWidth = 0.5f
                                )

                                Spacer(modifier = Modifier.weight(0.5f))

                                Box(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "Сумма пожертвования",
                                        color = Color.LightGray,
                                        textAlign = TextAlign.Center,
                                        fontSize = fontSizeFirst,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }

                                Spacer(modifier = Modifier.weight(0.1f))

                                Box(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "$" + sumSpons.toString(),
                                        color = Color.LightGray,
                                        textAlign = TextAlign.Center,
                                        fontSize = fontSizeFourth,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }

                                Spacer(modifier = Modifier.weight(0.1f))

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Button(
                                        modifier = Modifier
                                            .weight(0.2f)
                                            .aspectRatio(1f),
                                        onClick = {
                                            sumSpons -= sumChang
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color.LightGray,
                                            contentColor = Color.Black
                                        ),
                                    ) {
                                        Text("-", fontSize = fontSizeSecond)
                                    }

                                    OutlinedTextField(
                                        value = sumChangStr,
                                        onValueChange = {
                                            sumChangStr = it
                                            sumChang = it.toIntOrNull() ?: 0
                                                        },
                                        placeholder = { Text("...", fontSize = fontSizeSecond) },
                                        textStyle = androidx.compose.ui.text.TextStyle(
                                            fontSize = fontSizeSecond,
                                            color = Color.Black,
                                            textAlign = TextAlign.Center
                                        ),
                                        modifier = Modifier
                                            .weight(0.6f)
                                            .padding(horizontal = 4.dp),
                                        singleLine = true
                                    )

                                    Button(
                                        modifier = Modifier
                                            .weight(0.2f)
                                            .aspectRatio(1f),
                                        onClick = {
                                            sumSpons += sumChang
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color.LightGray,
                                            contentColor = Color.Black
                                        ),
                                    ) {
                                        Text("+", fontSize = fontSizeSecond)
                                    }
                                }

                                Spacer(modifier = Modifier.weight(0.5f))

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                ) {
                                    Button(
                                        onClick = {
                                            val selectedFond = fonds.find { it.name == selectedFondName }
                                            if (selectedFond != null) {
                                                val result = viewModel.topUpBalance(selectedFond.id, sumSpons)
                                                if (result){
                                                    navigator.push(ConfirmSponsorScreen(timerViewModel, sumSpons.toString(), runner, selectedFondName))
                                                }
                                            }
                                        },
                                        modifier = Modifier.weight(1f),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color.LightGray,
                                            contentColor = Color.Black
                                        ),
                                        shape = RoundedCornerShape(15.dp)
                                    ) {
                                        Text("Заплатить", fontSize = fontSizeThird)
                                    }

                                    Spacer(modifier = Modifier.weight(0.1f))

                                    Button(
                                        onClick = { navigator.push(MainScreen()) },
                                        modifier = Modifier.weight(1f),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color.LightGray,
                                            contentColor = Color.Black
                                        ),
                                        shape = RoundedCornerShape(15.dp)
                                    ) {
                                        Text("Отмена", fontSize = fontSizeThird)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun ForSponsorTopBar() {
        val navigator = LocalNavigator.currentOrThrow

        Box {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                val fontSizeButton = (maxWidth.value / 50).sp
                val fontSizeText = (maxWidth.value / 25).sp

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Button(
                        onClick = { navigator.push(MainScreen()) },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.LightGray,
                            contentColor = Color.Black
                        ),
                        elevation = null,
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier
                            .wrapContentWidth()

                    ) {
                        Text(
                            text = "Назад",
                            color = Color.Black,
                            fontSize = fontSizeButton
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    Text(
                        text = "MARATHON SKILLS 2016",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSizeText,
                        color = Color.White
                    )
                }
            }
        }
    }



    @Composable
    private fun ForSposnorBottomBar(timerViewModel: TimerViewModel) {
        val remainingTime = timerViewModel.remainingTime.collectAsState().value
        Box {
            BoxWithConstraints(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                val fontSize = (maxWidth.value / 50).sp

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = remainingTime,
                        color = Color.White,
                        fontSize = fontSize
                    )
                }
            }
        }
    }
}