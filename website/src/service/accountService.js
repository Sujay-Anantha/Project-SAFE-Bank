// import axios from 'react-axios';

const URL="http://localhost:3306/account";
class AccountService {

    // createAccount(Account){
    //     return axios.post(`${URL}/create`, Account);
    // }
    // static createAccount = (account) => { 
    //     fetch(`${URL}/create`, account,
    //     {
    //         method: "POST",
    //         mode: 'cors',
    //         headers: {
    //             'Content-Type': 'application/json'
    //         }
    //     })
    //     .then(res => res.json());
    // }

    // getAccountByAccountNumber(accountNumber){
    //     return axios.get(`${URL}/${accountNumber}`);
    // }
    // static getAccountByAccountNumber(accountNumber){
    //     return fetch(`${URL}/`, accountNumber,
    //     {
    //         method: "GET",
    //         mode: 'cors',
    //         headers: {
    //             'Content-Type': 'application/json'
    //         }
    //     })
    //     .then(res => res.json());
    // }

    // static getAllAccountDetails(){
    //     return axios.get(`${URL}/getallaccount`);
    // }
    static getAllAccountDetails(){
        return fetch(`${URL}/getallaccount`,
        {
            method: "GET",
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(res => res.json());
    }

    // updateAccountDetails(acct_no){
    //     return axios.put(`${URL}/update/`, acct_no);
    // }   
    static updateAccountDetails(acct_no){
        return fetch(`${URL}/update/`, acct_no,
        {
            method: "PUT",
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(res => res.json());
    }   

    // deleteAccount(acct_no){
    //     return axios.delete(`${URL}/delete/${acct_no}`);
    // }
    static deleteAccount(acct_no){
        return fetch(`${URL}/delete/${acct_no}`,
        {
            method: "DELETE",
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(res => res.json());
    }


}export default new AccountService();