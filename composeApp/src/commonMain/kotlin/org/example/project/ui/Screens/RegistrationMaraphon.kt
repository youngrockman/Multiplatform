package org.example.project.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
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
import org.example.project.Components.FieldWithLabelInt
import org.example.project.ViewModel.TimerViewModel
import org.example.project.ViewModel.UserViewModel

class RegMaraphonScreen(private val timerViewModel: TimerViewModel, private val userId: Int) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = UserViewModel()
        val fonds = viewModel.getFond()

        Scaffold(
            topBar = { RegMaraphonTopBar() },
            bottomBar = { RegMaraphonBottomBar(timerViewModel = timerViewModel) }
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
                    var finalSum by remember { mutableStateOf(0) }
                    var previousAddonPrice by remember { mutableStateOf(0) }
                    var checked1 by remember { mutableStateOf(false) }
                    var checked2 by remember { mutableStateOf(false) }
                    var checked3 by remember { mutableStateOf(false) }
                    val options = listOf(
                        "Вариант А($0): Номер бегуна + RFID браслет",
                        "Вариант B($20): вариант A + бейсболка + бутылка воды",
                        "Вариант C($45): Вариант B + футболка + сувенирный буклет"
                    )
                    var fondsNames = fonds.map { it.name }
                    var selectedOption by remember { mutableStateOf(options[0]) }
                    var vznos by remember { mutableStateOf("")}

                    Column(
                        modifier = Modifier
                            .fillMaxWidth().padding(bottom = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Регистрация на марафон",
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center,
                            fontSize = fontSizeFirst,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.weight(0.1f))

                        Text(
                            text = "Пожалуйста, заполните всю информацию, чтобы зарегестрироваться на Skills Marathon 2016 проводимом в Москве. Russia. С вами свяжутся после регистрации для уточнения оплаты и другой информации.",
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
                            Column(modifier = Modifier.weight(1f), ) {
                                Spacer(modifier = Modifier.weight(0.1f))

                                Text(
                                    text = "Вид марафона",
                                    color = Color.LightGray,
                                    textAlign = TextAlign.Center,
                                    fontSize = fontSizeFirst,
                                    modifier = Modifier
                                )

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Checkbox(
                                        checked = checked1,
                                        onCheckedChange = { isChecked ->
                                            checked1 = isChecked
                                            if (isChecked) {
                                                finalSum += 145
                                            } else {
                                                finalSum -= 145
                                            }
                                        }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = "42km Полный марафон($145)", fontSize = fontSizeSecond)
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Checkbox(
                                        checked = checked2,
                                        onCheckedChange = { isChecked ->
                                            checked2 = isChecked
                                            if (isChecked) {
                                                finalSum += 75
                                            } else {
                                                finalSum -= 75
                                            }
                                        }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = "21km Поулмарафон($75)", fontSize = fontSizeSecond)
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Checkbox(
                                        checked = checked3,
                                        onCheckedChange = { isChecked ->
                                            checked3 = isChecked
                                            if (isChecked) {
                                                finalSum += 20
                                            } else {
                                                finalSum -= 20
                                            }
                                        }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = "5km Малая дистанция($20)", fontSize = fontSizeSecond)
                                }

                                Text(
                                    text = "Детали спонсорства",
                                    color = Color.LightGray,
                                    textAlign = TextAlign.Center,
                                    fontSize = fontSizeFirst,
                                    modifier = Modifier
                                )

                                FieldWithDropdown(
                                    label = "Взнос:",
                                    options = fondsNames,
                                    selectedOption = vznos,
                                    onOptionSelected = { vznos = it },
                                    fontSize = fontSizeSecond,
                                    labelWidth = 0.5f
                                )

                                Spacer(modifier = Modifier.weight(0.1f))

                                FieldWithLabelInt(
                                    label = "Сумма взноса:",
                                    value = finalSum,
                                    onValueChange = { finalSum = it },
                                    placeholder = "$$$",
                                    fontSize = fontSizeSecond,
                                    labelWidth = 0.5f,
                                    enabled = false
                                )

                                Spacer(modifier = Modifier.weight(0.1f))

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                ) {
                                    Button(
                                        onClick = {
                                            val selectedFond = fonds.find { it.name == vznos }
                                            if (selectedFond != null && selectedFond.balance > finalSum) {
                                                val result = viewModel.withdrawalBalance(selectedFond.id, finalSum)
                                                val result2 = viewModel.updateUserActive(userId)
                                                if (result && result2){
                                                    navigator.push(ConfirmRunnerScreen(timerViewModel, userId))
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
                                        Text("Регистрация", fontSize = fontSizeThird)
                                    }

                                    Spacer(modifier = Modifier.weight(0.1f))

                                    Button(
                                        onClick = { navigator.push(InfoRunnerScreen(timerViewModel, userId)) },
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

                            Column(modifier = Modifier.weight(1f)) {
                                Spacer(modifier = Modifier.weight(0.1f))

                                Box(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "Варианты комплектов",
                                        color = Color.LightGray,
                                        textAlign = TextAlign.Center,
                                        fontSize = fontSizeFirst,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }

                                options.forEach { text ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        RadioButton(
                                            selected = (text == selectedOption),
                                            onClick = {
                                                val newAddonPrice = when (text) {
                                                    options[0] -> 0
                                                    options[1] -> 20
                                                    options[2] -> 45
                                                    else -> 0
                                                }

                                                finalSum = finalSum - previousAddonPrice + newAddonPrice

                                                previousAddonPrice = newAddonPrice
                                                selectedOption = text
                                            }
                                        )
                                        Spacer(modifier = Modifier.weight(0.01f))
                                        Text(text = text, fontSize = fontSizeSecond)
                                    }
                                }

                                Spacer(modifier = Modifier.weight(0.1f))

                                Box(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "Регистрационный взнос",
                                        color = Color.LightGray,
                                        textAlign = TextAlign.Center,
                                        fontSize = fontSizeFirst,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }

                                Spacer(modifier = Modifier.weight(0.1f))

                                Box(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "$" + finalSum.toString(),
                                        color = Color.LightGray,
                                        textAlign = TextAlign.Center,
                                        fontSize = fontSizeFourth,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun RegMaraphonTopBar() {
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
                        onClick = { navigator.push(InfoRunnerScreen(timerViewModel, userId)) },
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

                    Spacer(modifier = Modifier.width(64.dp))

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
                            text = "Logout",
                            color = Color.Black,
                            fontSize = fontSizeButton
                        )
                    }
                }
            }
        }
    }



    @Composable
    private fun RegMaraphonBottomBar(timerViewModel: TimerViewModel) {
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