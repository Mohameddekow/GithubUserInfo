package com.dekow.githubuserinfo.presentation.components


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.dekow.githubuserinfo.R
import com.dekow.githubuserinfo.domain.model.User
import com.dekow.githubuserinfo.presentation.users_list.UserViewModel
import com.dekow.githubuserinfo.ui.theme.BackgroundViolet
import com.dekow.githubuserinfo.ui.theme.ItemBackgroundWhite
import com.dekow.githubuserinfo.ui.theme.MyCustomShapes


@Composable
fun UsersListScreen(
    viewModel: UserViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Log.d("userTest", state.usersList.toString())


    Column(
        modifier = Modifier
            .background(BackgroundViolet)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        FeaturedDevsRow()

        FilterDevsRow()

        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clip(MyCustomShapes.large)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                contentPadding = PaddingValues(5.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                items(state.usersList) { user ->
                    UserItem(
                        user = user,
                    )
                }
            }

            //error or loading state
            if(state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if(state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }


}

@Composable
fun FeaturedDevsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(
            text = "Featured",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 38.sp,
            modifier = Modifier
                .padding(start = 4.dp)
        )
        Icon(
            Icons.Rounded.Search,
            contentDescription = "Search",
            modifier = Modifier
                .padding(5.dp)
                .size(33.dp)
                .clickable {
                    //clicks
                }
        )

    }
}

@Composable
fun FilterDevsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(
            text = "Sort Developers",
            textAlign = TextAlign.Start,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 4.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_sort),
            contentDescription = "filter",
            modifier = Modifier
                .padding(start = 5.dp, end = 10.dp)
                .size(29.dp)
                .clickable {
                    //clicks
                }
        )
    }
}


@Composable
fun UserItem(
    user: User,
    //onIconClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(ItemBackgroundWhite),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
    ) {
        Box(
            modifier = Modifier
                .widthIn(110.dp)
                .heightIn(130.dp)
                .clip(RoundedCornerShape(15.dp))
        ) {


            Image(
                painter = rememberAsyncImagePainter(user.imageUrl),
                contentDescription = "image",
                modifier = Modifier.size(130.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 5.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = user.name,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 5.dp, top = 2.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = user.node,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 5.dp, top = 5.dp),
                fontWeight = FontWeight.Light,
            )
            Text(
                text = user.repos,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 5.dp, top = 5.dp),
                fontWeight = FontWeight.Light,
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun UerPrev() {
    UsersListScreen()
}