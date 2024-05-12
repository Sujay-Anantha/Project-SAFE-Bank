// import DOMPurify from 'dompurify';
import React, { useState } from 'react';

import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import Stack from '@mui/material/Stack';
import Table from '@mui/material/Table';
import Button from '@mui/material/Button';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import Container from '@mui/material/Container';
import TableBody from '@mui/material/TableBody';
import TextField from '@mui/material/TextField';
import InputLabel from '@mui/material/InputLabel';
import Typography from '@mui/material/Typography';
import FormControl from '@mui/material/FormControl';
import TableContainer from '@mui/material/TableContainer';
import { Dialog, DialogTitle, DialogActions, DialogContent } from '@mui/material';

import { visuallyHidden } from '../utils';
import Iconify from '../../../components/iconify';
import { accounts } from '../../../_mock/accounts';
import AccountsTableRow from '../accounts-table-row';
import Scrollbar from '../../../components/scrollbar';
import AccountsTableHead from '../accounts-table-head';
// import accountService from '../../../service/accountService';
import customerService from '../../../service/customerService';
// import AccountsTableToolbar from '../accounts-table-toolbar';

// ----------------------------------------------------------------------

class AccountComponent extends React.Component {
  constructor(props){
    super(props);
    this.state={
      accounts:[]
    }
  }

  componentDidMount() {
    customerService.constructor.getCustomerDetailsByCustomerID(sessionStorage.getItem('custid')).then((res) => this.setState({accounts:res.accounts}));
  }

  
  render(){
    const accts = this.state;
    console.log(accts);
    return (
      <TableBody>
        {accts.accounts
          .map((row) => (
            <AccountsTableRow
              key={row.accountNumber}
              accNo={row.accountNumber}
              accName={row.accountName}
              street={row.street}
              city={row.city}
              state={row.state}
              zipcode={row.zipcode}
              dateOpened={row.dateOpened}
              status={row.status}
              // selected={selected.indexOf(row.accNo) !== -1}
              // handleClick={(event) => handleClick(event, row.accNo)}
              isAuthorized={isAuthorized}
            />
          ))}
      </TableBody>
    )
  }

}

const isAuthorized = () => {
  const role = sessionStorage.getItem('role');
  if (role === "customer") {
    return visuallyHidden
  }
  return 'none'
};

// async function addAccount(credentials) {
//   return fetch('http://localhost:8080/account/createChecking', {
//     method: 'POST',
//     headers: {
//       'Content-Type': 'application/json'
//     },
//     body: JSON.stringify(credentials)
//   })
// }

export default function AccountsView() {
  // const [page, setPage] = useState(0);

  const [order, setOrder] = useState('asc');

  // const [selected, setSelected] = useState([]);

  const [orderBy, setOrderBy] = useState('name');

  // const [filterName, setFilterName] = useState('');

  // const [rowsPerPage, setRowsPerPage] = useState(5);

  const handleSort = (event, id) => {
    const isAsc = orderBy === id && order === 'asc';
    if (id !== '') {
      setOrder(isAsc ? 'desc' : 'asc');
      setOrderBy(id);
    }
  };

  const [add, setAdd] = useState(false);

  const handleAddMenu = (event) => {
    setAdd(true);
  };

  const closeAddMenu = () => {
    setAdd(false);
  };

  // const handleSelectAllClick = (event) => {
  //   if (event.target.checked) {
  //     const newSelecteds = accounts.map((n) => n.name);
  //     setSelected(newSelecteds);
  //     return;
  //   }
  //   setSelected([]);
  // };

  // const handleClick = (event, name) => {
  //   const selectedIndex = selected.indexOf(name);
  //   let newSelected = [];
  //   if (selectedIndex === -1) {
  //     newSelected = newSelected.concat(selected, name);
  //   } else if (selectedIndex === 0) {
  //     newSelected = newSelected.concat(selected.slice(1));
  //   } else if (selectedIndex === selected.length - 1) {
  //     newSelected = newSelected.concat(selected.slice(0, -1));
  //   } else if (selectedIndex > 0) {
  //     newSelected = newSelected.concat(
  //       selected.slice(0, selectedIndex),
  //       selected.slice(selectedIndex + 1)
  //     );
  //   }
  //   setSelected(newSelected);
  // };

  // const handleChangePage = (event, newPage) => {
  //   setPage(newPage);
  // };

  // const handleChangeRowsPerPage = (event) => {
  //   setPage(0);
  //   setRowsPerPage(parseInt(event.target.value, 10));
  // };

  // const handleFilterByName = (event) => {
  //   setPage(0);
  //   setFilterName(event.target.value);
  // };

  // const dataFiltered = applyFilter({
  //   inputData: accounts,
  //   comparator: getComparator(order, orderBy),
  //   // filterName,
  // });


  
  const [addForm, setAddForm] = useState({ accountType: '', accountName: '', street: '', city: '', state: '', zipcode: '', dateOpened: '', status: '', serviceCharge: ''});
  
  const handleChange = (e) => {
    const {name, value} = e.target;
    setAddForm({ ...addForm, [name]: e.target.type === 'number' ? parseInt(value, 10) : value });
  };

  const handleAdd = () => {
    console.log(addForm);
    // const sanitizedAddForm=DOMPurify.sanitize(addForm);
    // const addstatus = addAccount(sanitizedAddForm);
    // console.log(addstatus);
    // addstatus.then(response =>
    //   response.json().then(json => ({
    //     status: response.status,
    //     json
    //   })
    // ))
    // .then(({ status, json }) => {
    //   console.log({ status, json });
    //   if (status === 201) {
      //   }
      // })
    closeAddMenu();
  }

  return (
    <Container>
      <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
        <Typography variant="h4">Accounts</Typography>

        <Button variant="contained" color="inherit" startIcon={<Iconify icon="eva:plus-fill" />} onClick={handleAddMenu} sx={isAuthorized}>
          New Account
        </Button>

        <Dialog 
        open={!!add}
        onClose={closeAddMenu}
        >
        <DialogTitle>Add Account</DialogTitle>
        <DialogContent>
          <Stack spacing={2} paddingTop={2}>
            <FormControl sx={{ minWidth: 120 }}>
              <InputLabel id="roleLabel">Account Type</InputLabel>
              <Select
                name="accountType"
                label="Account Type"
                value={addForm.accountType}
                onChange={handleChange}
              >
                <MenuItem value='C'>Checking</MenuItem>
                <MenuItem value='S'>Savings</MenuItem>
                <MenuItem value='L'>Loan</MenuItem>
              </Select>
            </FormControl>
            <TextField name="accountName" value={addForm.accountName} label="Account Name" onChange={handleChange}/>
            <TextField name="street" value={addForm.street} label="Street" onChange={handleChange}/>
            <TextField name="city" value={addForm.city} label="City" onChange={handleChange}/>

          </Stack>

          <Grid container justifyContent="flex-end"  sx={{ my: 3 }}>
            <Grid item xs={6}>
              <TextField name="state" value={addForm.state} label="State" onChange={handleChange}/>
            </Grid>
            <Grid item xs={6}>
              <TextField name="zipcode" value={addForm.zipcode} label="Zip Code" onChange={handleChange} type="number"/>
            </Grid>
          </Grid>

          <Stack spacing={2}>
            <TextField name="dateOpened" value={addForm.dateOpened} label="Date Opened" onChange = {handleChange} type="date"/>
            <TextField name="accountNumber" value={addForm.accountNumber} label="Account Number" onChange = {handleChange} type="number"/>
            <TextField name="serviceCharge" value={addForm.serviceCharge} label="Service Charge" onChange = {handleChange} type="number"/>
          </Stack>
        </DialogContent>

        <DialogActions>
          <Button
            fullWidth
            size="large"
            type="submit"
            variant="contained"
            color="inherit"
            onClick={() => handleAdd()}
          >
            Confirm
          </Button>
        </DialogActions>
        </Dialog>
      </Stack>
      
      <Card>
        {/* <Stack direction="row" alignItems="center" justifyContent="space-between" padding={2}>
          <Typography variant="h5">Checking</Typography>
        </Stack> */}
        

        <Scrollbar>
          <TableContainer sx={{ overflow: 'unset' }}>
            <Table sx={{ minWidth: 800 }}>
              <AccountsTableHead
                order={order}
                orderBy={orderBy}
                rowCount={accounts.length}
                // numSelected={selected.length}
                onRequestSort={handleSort}
                // onSelectAllClick={handleSelectAllClick}
                headLabel={[
                  { id: 'accNo', label: 'Account No' },
                  { id: 'accName', label: 'Account Name' },
                  { id: 'address', label: 'Address' },
                  { id: 'dateOpened', label: 'Date Opened', align: 'center' },
                  { id: 'status', label: 'Status' },
                  { id: '' },
                ]}
              />
              {/* <TableBody>
                {dataFiltered
                  .map((row) => (
                    <AccountsTableRow
                      key={row.id}
                      accNo={row.accNo}
                      accName={row.accName}
                      address={row.address}
                      dateOpened={row.dateOpened}
                      status={row.status}
                      // selected={selected.indexOf(row.accNo) !== -1}
                      // handleClick={(event) => handleClick(event, row.accNo)}
                    />
                  ))}

              </TableBody> */}
              <AccountComponent/>
            </Table>
          </TableContainer>
        </Scrollbar>

      </Card>

     </Container>
  );
}
