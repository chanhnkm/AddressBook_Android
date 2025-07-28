package com.example.addressbook250727.ui.objects

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.unit.dp
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.ViewModel

import com.example.addressbook250727.ui.theme.Transparent
import com.example.addressbook250727.ui.theme.PurpleGrey40

import com.example.addressbook250727.data.AddressBook
import com.example.addressbook250727.data.Personal

@Composable
fun AddressBookForm(
    viewModel: AddressBookViewModel,
    modifier: Modifier = Modifier
) {
    Column(modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // 1. mask: 입력 폼
        OutlinedTextField(
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            label = { Text("이름") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = viewModel.address,
            onValueChange = { viewModel.address = it },
            label = { Text("주소") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = viewModel.telephoneNumber,
            onValueChange = { viewModel.telephoneNumber = it },
            label = { Text("전화번호") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        OutlinedTextField(
            value = viewModel.emailAddress,
            onValueChange = { viewModel.emailAddress = it },
            label = { Text("이메일 주소") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(Modifier.height(16.dp))

        // 2. format: 테이블 같은 출력
        LazyColumn(modifier
            .weight(1f)
            .fillMaxWidth()) {
            itemsIndexed(viewModel.personals) { index, p ->
                val backgroundColor =
                    if (index == viewModel.selectedIndex) PurpleGrey40 else Transparent

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .clickable { viewModel.onListItemClicked(index) }
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = p.getName(), modifier = Modifier.weight(1f))
                        Text(text = p.getAddress(), modifier = Modifier.weight(2f))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = p.getTelephoneNumber(), modifier = Modifier.weight(1f))
                        Text(text = p.getEmailAddress(), modifier = Modifier.weight(2f))
                    }
                    //Divider()
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // 3. 버튼들
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { viewModel.onRecordButtonClicked() }) { Text("기재하기") }
            Button(onClick = { viewModel.onFindButtonClicked() }) { Text("찾기") }
            Button(onClick = { viewModel.onCorrectButtonClicked() }) { Text("고치기") }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { viewModel.onEraseButtonClicked() }) { Text("지우기") }
            Button(onClick = { viewModel.onArrangeButtonClicked() }) { Text("정리하기") }
            Button(onClick = { viewModel.onLoadButtonClicked() }) { Text("불러오기") }
        }
    }

}

class AddressBookViewModel : ViewModel {
    var name by mutableStateOf("")
    var address by mutableStateOf("")
    var telephoneNumber by mutableStateOf("")
    var emailAddress by mutableStateOf("")
    var personals = mutableStateListOf<Personal>()
    var selectedIndex by mutableStateOf(-1)

    val addressBook: AddressBook

    constructor() {
        this.addressBook = AddressBook()
    }

    fun onRecordButtonClicked() {
        val index = this.addressBook.record(
            this.name,
            this.address,
            this.telephoneNumber,
            this.emailAddress
        )
        val personal = this.addressBook.getAt(index)
        this.personals.add(personal)
    }

    fun onFindButtonClicked() {

    }

    fun onCorrectButtonClicked() {
        val index = this.addressBook.correct(
            this.selectedIndex,
            this.address,
            this.telephoneNumber,
            this.emailAddress
        )
        val personal = this.addressBook.getAt(index)
        personals[selectedIndex] = personal
    }

    fun onEraseButtonClicked() {
        this.addressBook.erase(this.selectedIndex)
        this.personals.removeAt(this.selectedIndex)
    }

    fun onArrangeButtonClicked() {
        this.personals.clear()
        this.addressBook.arrange()

        var personal: Personal
        var i = 0
        while (i < this.addressBook.getLength()) {
            personal = this.addressBook.getAt(i)
            this.personals.add(personal)
            i++
        }
    }

    fun onListItemClicked(index: Int) {
        val personal = this.addressBook.getAt(index)
        this.name = personal.getName()
        this.address = personal.getAddress()
        this.telephoneNumber = personal.getTelephoneNumber()
        this.emailAddress = personal.getEmailAddress()

        this.selectedIndex = index
    }

    fun onLoadButtonClicked() {
        var index =
            this.addressBook.record("HongKildong", "Mapo, Seoul", "01011111111", "hong@gmail.com")
        var personal = this.addressBook.getAt(index)
        this.personals.add(personal)
        index =
            this.addressBook.record("KoKildong", "Usung, Daejeon", "01022222222", "ko@gmail.com");
        personal = this.addressBook.getAt(index)
        this.personals.add(personal)
        index = addressBook.record("JungKildong", "West, Daegu", "01033333333", "jung@naver.com");
        personal = this.addressBook.getAt(index)
        this.personals.add(personal)
        index =
            addressBook.record("HongKildong", "Haeundae, Busan", "01044444444", "hong@naver.com");
        personal = this.addressBook.getAt(index)
        this.personals.add(personal)
    }
}