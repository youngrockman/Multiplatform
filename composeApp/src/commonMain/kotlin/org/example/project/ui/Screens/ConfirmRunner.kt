package org.example.project.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import org.example.project.ViewModel.TimerViewModel

class ConfirmRunnerScreen(private val timerViewModel: TimerViewModel, private val idRunner: Int) :
    Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = { ConfirmRunnerTopBar() },
            bottomBar = { ConfirmRunnerBottomBar(timerViewModel = timerViewModel) }
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

                    Column(
                        modifier = Modifier
                            .fillMaxWidth().padding(bottom = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.weight(0.2f))

                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Спасибо за вашу регистрацию в качестве бегуна!",
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                fontSize = fontSizeFirst,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        Spacer(modifier = Modifier.weight(0.2f))

                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Спасибо за вашу регистрацию в качестве бегуна в Marathon Skills 2016!\n С вами свяжутся по поводу оплаты.",
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                fontSize = fontSizeFirst,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        Spacer(modifier = Modifier.weight(0.2f))

                        Button(
                            onClick = { navigator.push(InfoRunnerScreen(timerViewModel, idRunner)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.LightGray,
                                contentColor = Color.Black
                            ),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text("Ок", fontSize = fontSizeSecond)
                        }

                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }

    @Composable
    private fun ConfirmRunnerTopBar() {
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
                        onClick = { },
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
    private fun ConfirmRunnerBottomBar(timerViewModel: TimerViewModel) {
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