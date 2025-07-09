export function isAuthenticated(){
    const token = localStorage.getItem('token');
    if(!token) return false;

    try{
        const payload = JSON.parse(atob(token.split('.')[1]));
        const now = Math.floor(Date.now() / 1000);

        if (payload.exp && payload.now > now){
            return true;
        } else{
            localStorage.clear();
            return false;
        }
    } catch(err){
        localStorage.clear();
        return false;
    }
}