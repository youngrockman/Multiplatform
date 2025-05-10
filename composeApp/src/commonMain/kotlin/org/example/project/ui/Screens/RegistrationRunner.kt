package org.example.project.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.Models.User
import org.example.project.ViewModel.DatePickerField
import org.example.project.Components.FieldWithDropdown
import org.example.project.Components.FieldWithLabel
import org.example.project.ViewModel.GetPath
import org.example.project.ViewModel.ImageFromPath
import org.example.project.ViewModel.TimerViewModel
import org.example.project.ViewModel.UserViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class RegRunnerScreen(
    private val timerViewModel: TimerViewModel,
) : Screen {

    @Composable
    override fun Content() {
        val viewModel = UserViewModel()
        var users = viewModel.getUsers()
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var secondPassword by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }
        var surname by remember { mutableStateOf("") }
        var gender by remember { mutableStateOf("") }
        var country by remember { mutableStateOf("") }
        var photoPath by remember { mutableStateOf("") }
        var openPicker by remember { mutableStateOf(false) }
        if (openPicker) {
            GetPath { selected ->
                photoPath = selected
                openPicker = false
            }
        }
        var showDatePicker by remember { mutableStateOf(false) }
        var dateBirthday by remember { mutableStateOf("") }
        if (showDatePicker) {
            DatePickerField(
                onDateSelected = {
                    dateBirthday = it
                },
                trigger = true,
                onDismissRequest = { showDatePicker = false }
            )
        }

        Scaffold(
            topBar = { RegRunnerTopBar() },
            bottomBar = {
                BottomSection(
                    timerViewModel,
                    email, password, secondPassword, name, surname, gender, photoPath, dateBirthday, country
                    )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues).fillMaxSize()
            ) {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(horizontal = 10.dp)
                ) {
                    val fontSize = (maxWidth.value / 60).sp
                    val fontSizeFirst = (maxWidth.value / 30).sp
                    val fontSizeSecond = (maxWidth.value / 40).sp
                    val labelWidth = 0.5f

                    Column(
                        modifier = Modifier
                            .fillMaxWidth().padding(bottom = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Регистрация бегуна",
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center,
                            fontSize = fontSizeFirst,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.weight(0.1f))

                        Text(
                            text = "Пожалуйста, заполните всю информацию, чтобы зарегестрироваться в качестве участника",
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
                            Column(modifier = Modifier.weight(0.8f)) {
                                Spacer(modifier = Modifier.weight(0.1f))

                                FieldWithLabel(
                                    label = "Email:",
                                    value = email,
                                    onValueChange = { email = it
                                                    println(email)},
                                    placeholder = "Email",
                                    fontSize = fontSize,
                                    labelWidth = labelWidth,
                                )

                                Spacer(modifier = Modifier.weight(0.1f))

                                FieldWithLabel(
                                    label = "Пароль:",
                                    value = password,
                                    onValueChange = { password = it },
                                    placeholder = "Пароль",
                                    fontSize = fontSize,
                                    labelWidth = labelWidth,
                                )

                                Spacer(modifier = Modifier.weight(0.1f))

                                FieldWithLabel(
                                    label = "Повторите пароль:",
                                    value = secondPassword,
                                    onValueChange = { secondPassword = it },
                                    placeholder = "Повторите пароль",
                                    fontSize = fontSize,
                                    labelWidth = labelWidth,
                                )

                                Spacer(modifier = Modifier.weight(0.1f))

                                FieldWithLabel(
                                    label = "Имя:",
                                    value = name,
                                    onValueChange = { name = it },
                                    placeholder = "Имя",
                                    fontSize = fontSize,
                                    labelWidth = labelWidth,
                                )

                                Spacer(modifier = Modifier.weight(0.1f))

                                FieldWithLabel(
                                    label = "Фамилия:",
                                    value = surname,
                                    onValueChange = { surname = it },
                                    placeholder = "Фамилия",
                                    fontSize = fontSize,
                                    labelWidth = labelWidth,
                                )

                                Spacer(modifier = Modifier.weight(0.1f))

                                FieldWithDropdown(
                                    label = "Пол:",
                                    options = listOf("Мужской", "Женский"),
                                    selectedOption = gender,
                                    onOptionSelected = { gender = it },
                                    fontSize = fontSize,
                                    labelWidth = labelWidth
                                )
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                ImageFromPath(
                                    path = photoPath,
                                    modifier = Modifier
                                        .weight(0.4f)
                                        .clip(RoundedCornerShape(8.dp))
                                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)).align(Alignment.End)
                                )

                                Spacer(modifier = Modifier.weight(0.05f))

                                Column(modifier = Modifier.weight(0.3f)) {
                                    Text(
                                        text = "Фото файл:",
                                        color = Color.DarkGray,
                                        textAlign = TextAlign.Start,
                                        fontSize = fontSize,
                                        modifier = Modifier
                                    )

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {

                                        OutlinedTextField(
                                            value = photoPath,
                                            onValueChange = {photoPath = it},
                                            placeholder = {
                                                    Text(
                                                        text = "Photo_logo.jpg",
                                                        fontSize = fontSize,
                                                        color = Color.LightGray
                                                    )
                                            },
                                            textStyle = androidx.compose.ui.text.TextStyle(
                                                fontSize = fontSize,
                                                color = Color.Black
                                            ),
                                            enabled = true,
                                            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                                                backgroundColor = Color.White,
                                                cursorColor = Color.Black,
                                                textColor = Color.Black,
                                                placeholderColor = Color.LightGray,
                                                focusedBorderColor = Color(0xFFCCCCCC),
                                                unfocusedBorderColor = Color(0xFFCCCCCC),
                                                disabledBorderColor = Color(0xFFCCCCCC)
                                            ),
                                            modifier = Modifier.weight(0.6f).wrapContentWidth().fillMaxWidth()
                                        )

                                        Spacer(modifier = Modifier.weight(0.05f))

                                        Button(
                                            onClick = { openPicker = true},
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .weight(0.5f),
                                            colors = ButtonDefaults.buttonColors(
                                                backgroundColor = Color.LightGray,
                                                contentColor = Color.Black
                                            ),
                                            shape = RoundedCornerShape(15.dp)
                                        ) {
                                            Text(
                                                "Просмотр",
                                                fontSize = fontSize
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.weight(0.05f))

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Box(modifier = Modifier.weight(labelWidth)) {
                                            Text(
                                                text = "Дата рождения",
                                                color = Color.DarkGray,
                                                textAlign = TextAlign.Start,
                                                fontSize = fontSize,
                                                modifier = Modifier
                                            )
                                        }

                                        OutlinedTextField(
                                            value = dateBirthday,
                                            onValueChange = {dateBirthday = it},
                                            placeholder = {
                                                Text(
                                                    text = "...",
                                                    fontSize = fontSize,
                                                    color = Color.LightGray
                                                )
                                            },
                                            textStyle = androidx.compose.ui.text.TextStyle(
                                                fontSize = fontSize,
                                                color = Color.Black
                                            ),
                                            enabled = true,
                                            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                                                backgroundColor = Color.White,
                                                cursorColor = Color.Black,
                                                textColor = Color.Black,
                                                placeholderColor = Color.LightGray,
                                                focusedBorderColor = Color(0xFFCCCCCC),
                                                unfocusedBorderColor = Color(0xFFCCCCCC),
                                                disabledBorderColor = Color(0xFFCCCCCC)
                                            ),
                                            modifier = Modifier.weight(0.9f).wrapContentWidth()
                                        )

                                        Spacer(modifier = Modifier.weight(0.05f))

                                        Box(
                                            modifier = Modifier
                                                .weight(0.5f)
                                                .fillMaxWidth()
                                                .clip(RoundedCornerShape(15.dp))
                                                .background(Color.LightGray)
                                                .clickable { showDatePicker = true }
                                                .padding(vertical = 8.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.DateRange,
                                                contentDescription = "Выбрать дату",
                                                tint = Color.Black,
                                                modifier = Modifier.size(24.dp)
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.weight(0.05f))

                                    FieldWithDropdown(
                                        label = "Страна:",
                                        options = listOf("Russia", "Other"),
                                        selectedOption = country,
                                        onOptionSelected = { country = it },
                                        fontSize = fontSize,
                                        labelWidth = labelWidth
                                    )

                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun RegRunnerTopBar() {
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

                    Spacer(modifier = Modifier.width(64.dp))
                }
            }
        }
    }

    @Composable
    private fun RegRunnerBottomBar(timerViewModel: TimerViewModel) {
        val remainingTime = timerViewModel.remainingTime.collectAsState().value

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = remainingTime,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }

    @Composable
        private fun BottomSection(
            timerViewModel: TimerViewModel,
            email: String,
            password: String,
            secondPassword: String,
            name: String,
            surname: String,
            gender: String,
            photoPath: String,
            dateBirthday: String,
            country: String
        ){
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            ) {
                Button(
                    onClick = {
                        val viewModel = UserViewModel()
                        var users = viewModel.getUsers()
                        val nextId = (users.maxByOrNull { it.id }?.id ?: 0) + 1
                        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                        val birthdayDate = if (dateBirthday.isNotEmpty()) {
                            try {
                                LocalDate.parse(dateBirthday, formatter)
                            } catch (e: DateTimeParseException) {
                                LocalDate.now()
                            }
                        } else {
                            LocalDate.now()
                        }

                        if (secondPassword == password) {
                            val result = viewModel.regUser(
                                User(
                                    id = nextId,
                                    email = email,
                                    password = password,
                                    name = name,
                                    surname = surname,
                                    gender = gender,
                                    photopath = photoPath,
                                    birthday = birthdayDate,
                                    country = country,
                                    active = false
                                )
                            )
                            if (result){
                                navigator.push(InfoRunnerScreen(timerViewModel, nextId))
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
                    Text("Регистрация")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { navigator.push(MainScreen()) },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.LightGray,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text("Отмена")
                }
            }

            RegRunnerBottomBar(timerViewModel = timerViewModel)
        }
    }
}

