package com.dekow.githubuserinfo.presentation.components


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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.dekow.githubuserinfo.R
import com.dekow.githubuserinfo.domain.model.User
import com.dekow.githubuserinfo.domain.model.UserDetails
import com.dekow.githubuserinfo.presentation.screen_routes.Screens
import com.dekow.githubuserinfo.presentation.users_list.UserListViewModel
import com.dekow.githubuserinfo.ui.theme.*


@Composable
fun UsersListScreen(
    navController: NavHostController,
    listViewModel: UserListViewModel = hiltViewModel()
) {
    val state = listViewModel.userListState.value

    Column(
        modifier = Modifier
            .background(BackgroundViolet)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        FeaturedDevsRow(navController = navController)

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
                        ListItemBackgroundWhite
                    )
                }
            }

           // error or loading state
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
fun FeaturedDevsRow(
    navController: NavHostController
) {

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

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(46.dp)
                .width(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(BackgroundBox)
                .clickable {
//                    navController.navigate((Screens.UserDetailsScreen.route + "/mohameddekow"))
                    navController.navigate((Screens.UserSearchScreen.route))

                },

            ) {

//            var text = ""
//            OutlinedTextField(
//                value = text,
//                onValueChange = { text = it },
//
//            )

            Icon(
                Icons.Rounded.Search,
                contentDescription = "Search",
                modifier = Modifier
                    .padding(5.dp)
                    .size(33.dp)

            )
        }


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
    colors: Color
    //onIconClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color = colors),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
    ) {
        Box(
            modifier = Modifier
                .widthIn(90.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(15.dp))
        ) {


            //Image(painter = painterResource(user.placeHolderId!!), contentDescription = "placeholder")

           Image(
                painter = rememberAsyncImagePainter(user.imageUrl),
                contentDescription = "image",
                modifier = Modifier.size(100.dp)
            )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = user.name,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 5.dp, top = 4.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "${user.node} - ${user.id}",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 5.dp, top = 5.dp),
                fontWeight = FontWeight.Light,
            )
            Text(
                text = user.html,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 5.dp, top = 5.dp),
                fontWeight = FontWeight.Light,
            )

//            Text(
//                text = user.name,
//                textAlign = TextAlign.Start,
//                modifier = Modifier
//                    .padding(start = 5.dp, top = 4.dp),
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//            )
//            Text(
//                text = "${user.node} - ${user.id}",
//                textAlign = TextAlign.Start,
//                modifier = Modifier
//                    .padding(start = 5.dp, top = 5.dp),
//                fontWeight = FontWeight.Light,
//            )
//            Text(
//                text = user.html,
//                textAlign = TextAlign.Start,
//                modifier = Modifier
//                    .padding(start = 5.dp, top = 5.dp),
//                fontWeight = FontWeight.Light,
//            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun UerPrev() {
    UsersListScreen(navController = rememberNavController())
}