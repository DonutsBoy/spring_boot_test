<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="info">
            <b-navbar-brand to="/">Vue.js 스터디</b-navbar-brand>
            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
            <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
                <b-nav-item href="#" to="/auto/dayprofit">일별수익</b-nav-item>
                <!-- <b-nav-item href="#" to="/upbit/api">업비트API</b-nav-item> -->
                <b-nav-item href="#" to="/todo">할일</b-nav-item>
                <b-nav-item href="#" to="/todo2">할일2</b-nav-item>
                <b-nav-item href="#" to="/board">게시판</b-nav-item>
                <b-nav-item href="#" to="/login">로그인</b-nav-item>
                <b-nav-item href="#" to="/draw">추첨</b-nav-item>
                <!--<b-nav-item href="#" disabled>Disabled</b-nav-item>-->
            </b-navbar-nav>
            <b-navbar-nav class="ml-auto">
                <b-nav-form>
                <b-form-input size="sm" class="mr-sm-2" placeholder="검색어 입력"></b-form-input>
                <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
                </b-nav-form>
            </b-navbar-nav>                
    <!--        
            <b-navbar-nav class="ml-auto">
                <b-nav-form>
                <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
                </b-nav-form>

                <b-nav-item-dropdown text="Lang" right>
                <b-dropdown-item href="#">EN</b-dropdown-item>
                <b-dropdown-item href="#">ES</b-dropdown-item>
                <b-dropdown-item href="#">RU</b-dropdown-item>
                <b-dropdown-item href="#">FA</b-dropdown-item>
                </b-nav-item-dropdown>

                <b-nav-item-dropdown right>
                <template #button-content>
                    <em>User</em>
                </template>
                <b-dropdown-item href="#">Profile</b-dropdown-item>
                <b-dropdown-item href="#">Sign Out</b-dropdown-item>
                </b-nav-item-dropdown>
            </b-navbar-nav>
    -->
            </b-collapse>

        </b-navbar>
    </div>
</template>

<script>
export default {
    //name: "header",
};
</script>