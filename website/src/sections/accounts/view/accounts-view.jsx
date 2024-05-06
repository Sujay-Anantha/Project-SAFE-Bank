import DOMPurify from 'dompurify';
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
// import TablePagination from '@mui/material/TablePagination';
import { Dialog, DialogTitle, DialogActions, DialogContent } from '@mui/material';

import Iconify from '../../../components/iconify';
import { accounts } from '../../../_mock/accounts';
import Scrollbar from '../../../components/scrollbar';
import accountService from '../../../service/accountService';

// import TableNoData from '../table-no-data';
// import TableEmptyRows from '../table-empty-rows';
import AccountsTableRow from '../accounts-table-row';
import AccountsTableHead from '../accounts-table-head';
import { applyFilter, getComparator, visuallyHidden } from '../utils';
// import AccountsTableToolbar from '../accounts-table-toolbar';

// ----------------------------------------------------------------------

class AccountComponent extends React.Component {
  constructor(props){
    super(props);
    // this.state={
    //     id:this.props.match.params.id,
    //    userId:null,
    //    password:null
        
    // }
    this.state={
      accounts:[]
    }

    // this.changeIdHandler= this.changeIdHandler.bind(this);
    // this.changeNameHandler=this.changeNameHandler.bind(this);
    // this.signup=this.signup.bind(this);
    // this.login=this.login.bind(this);
  }

  componentDidMount() {
    accountService.constructor.getAllAccountDetails().then((res) => this.setState({accounts:res}));
  }
  
  render(){
    const accts = this.state;
    // console.log(accts.accounts);
    return (
      // <div>
      //   {accts.accounts.map((row) => (  
      //     <tr key={row.acct_no}>
      //       <td>{row.acct_name}</td>
      //       <td>{row.astreet}</td>
      //       <td>{row.date_opened}</td>
      //       <td>{row.astatus}</td>
      //     </tr>        
      //   ))}
      // </div>
      <TableBody>
        {accts.accounts
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

        {/* {notFound && <TableNoData query={filterName} />} */}
      </TableBody>


    )
  }

}

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

  const dataFiltered = applyFilter({
    inputData: accounts,
    comparator: getComparator(order, orderBy),
    // filterName,
  });

  const isAuthorized = () => {
    // TODO: get customer role
    if (add) {
      return visuallyHidden
    }
    return 'none'
  };
  
  const [addForm, setAddForm] = useState({ acctype: '', acct_name: '', astreet: '', acity: '', astate: '', azipcode: ''});
  
  const handleChange = (e) => {
    const {name, value} = e.target;
    setAddForm({ ...addForm, [name]: value });
  };

  const handleAdd = () => {
    console.log(addForm);
    const sanitizedAddForm=DOMPurify.sanitize(addForm);
    console.log(sanitizedAddForm);
    // TODO: call API to add account
    closeAddMenu();
  }

  // const notFound = !dataFiltered.length && !!filterName;

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
                name="acctype"
                label="Account Type"
                // labelId="ac"
                value={addForm.acctype}
                onChange={handleChange}
              >
                <MenuItem value='checking'>Checking</MenuItem>
                <MenuItem value='savings'>Savings</MenuItem>
                <MenuItem value='loan'>Loan</MenuItem>
              </Select>
            </FormControl>
            <TextField name="acct_name" value={addForm.acct_name} label="Account Name" onChange = {handleChange}/>
            <TextField name="astreet" value={addForm.astreet} label="Street" onChange = {handleChange}/>
            <TextField name="acity" value={addForm.acity} label="City" onChange = {handleChange}/>

          </Stack>

          <Grid container justifyContent="flex-end"  sx={{ my: 3 }}>
            <Grid item xs={6}>
              <TextField name="astate" value={addForm.astate} label="State" onChange = {handleChange}/>
            </Grid>
            <Grid item xs={6}>
              <TextField name="azipcode" value={addForm.azipcode} label="Zip Code" onChange = {handleChange}/>
            </Grid>
          </Grid>
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
      
      {/* Checking Account */}
      <Card>
        <Stack direction="row" alignItems="center" justifyContent="space-between" padding={2}>
          <Typography variant="h5">Checking</Typography>
        </Stack>
        

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
              <TableBody>
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

              </TableBody>
              <AccountComponent/>
            </Table>
          </TableContainer>
        </Scrollbar>

      </Card>

      {/* Savings Account */}
      <Card>
        <Stack direction="row" alignItems="center" justifyContent="space-between" padding={2}>
          <Typography variant="h5">Savings</Typography>
        </Stack>

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
              <TableBody>
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


                {/* {notFound && <TableNoData query={filterName} />} */}
              </TableBody>
            </Table>
          </TableContainer>
        </Scrollbar>
      </Card>

      {/* Loan Account */}
      <Card>
      <Stack direction="row" alignItems="center" justifyContent="space-between" padding={2}>
          <Typography variant="h5">Loan</Typography>
        </Stack>

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
              <TableBody>
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

                {/* {notFound && <TableNoData query={filterName} />} */}
              </TableBody>
            </Table>
          </TableContainer>
        </Scrollbar>

      </Card>
    </Container>
  );
}
