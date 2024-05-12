// import axios from 'react-axios';

const URL="http://localhost:8080/customer";
class AccountService {

    static getCustomerDetailsByCustomerID(custId){
        return fetch(`${URL}/${custId}`,
        {
            method: "GET",
            // mode: 'cors',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(res => res.json());
    }


}export default new AccountService();