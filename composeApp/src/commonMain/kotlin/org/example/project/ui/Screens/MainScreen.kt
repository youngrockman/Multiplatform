package org.example.project.ui.Screens

import org.example.project.ViewModel.TimerViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class MainScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val timerViewModel = TimerViewModel()

        Scaffold(
            topBar = { MainWindowTopBar() },
            bottomBar = { MainWindowBottomBar(timerViewModel) }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(horizontal = 100.dp)
                ) {
                    val fontSize = (maxWidth.value / 25).sp

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.weight(0.25f))

                        Button(
                            onClick = { navigator.push(RunnerScreen(timerViewModel)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.LightGray,
                                contentColor = Color.Black
                            ),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text("Я хочу стать бегуном", fontSize = fontSize)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { navigator.push(ForSponsorScreen(timerViewModel))},
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.LightGray,
                                contentColor = Color.Black
                            ),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text("Я хочу стать спонсором бегуна", fontSize = fontSize)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { navigator.push(MoreInfoScreen(timerViewModel)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.LightGray,
                                contentColor = Color.Black
                            ),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text("Я хочу узнать больше о событии", fontSize = fontSize)
                        }

                        Spacer(modifier = Modifier.weight(0.5f))
                    }
                }

                Button(
                    onClick = { navigator.push(LoginScreen(timerViewModel)) },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .height(65.dp)
                        .padding(end = 16.dp, bottom = 20.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.LightGray,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text("Login")
                }
            }
        }
    }

    @Composable
    private fun MainWindowTopBar() {
        Box {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                val fontSize = (maxWidth.value / 25).sp

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "MARATHON SKILLS 2016",
                        fontSize = fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Москва, Россия\nсреда 21 октября 2016",
                        textAlign = TextAlign.Center,
                        fontSize = fontSize,
                        fontStyle = FontStyle.Italic,
                        color = Color.LightGray
                    )
                }
            }
        }
    }

    @Composable
    private fun MainWindowBottomBar(timerViewModel: TimerViewModel) {
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
                    Text(text = remainingTime,
                        color = Color.White,
                        fontSize = fontSize)
                }
            }
        }
    }
}
