let currentUserData;
document.addEventListener('DOMContentLoaded', async function () {
    currentUserData = await dataAboutCurrentUser();
    await showUserEmailOnNavbar()
    await fillTableAboutCurrentUser();
});

