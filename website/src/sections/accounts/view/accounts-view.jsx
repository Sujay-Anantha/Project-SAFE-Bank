import { useState } from 'react';

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

import { accounts } from 'src/_mock/accounts';

import Iconify from 'src/components/iconify';
import Scrollbar from 'src/components/scrollbar';

// import TableNoData from '../table-no-data';
// import TableEmptyRows from '../table-empty-rows';
import AccountsTableRow from '../accounts-table-row';
import { applyFilter, getComparator } from '../utils';
import AccountsTableHead from '../accounts-table-head';
// import AccountsTableToolbar from '../accounts-table-toolbar';

// ----------------------------------------------------------------------

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

  const [edit, setEdit] = useState(false);

  const handleEditMenu = (event) => {
    setEdit(true);
  };

  const closeEditMenu = () => {
    setEdit(false);
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

  const [acctype, setType] = useState('');

  const handleChange = (event) => {
    setType(event.target.value);
  };

  // const notFound = !dataFiltered.length && !!filterName;

  return (
    <Container>
      <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
        <Typography variant="h4">Accounts</Typography>

        <Button variant="contained" color="inherit" startIcon={<Iconify icon="eva:plus-fill" />} onClick={handleEditMenu}>
          New Account
        </Button>

        <Dialog 
        open={!!edit}
        onClose={closeEditMenu}
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
                value={acctype}
                onChange={handleChange}
              >
                <MenuItem value='checking'>Checking</MenuItem>
                <MenuItem value='savings'>Savings</MenuItem>
                <MenuItem value='loan'>Loan</MenuItem>
              </Select>
            </FormControl>
            <TextField name="accountName" label="Account Name" />
            <TextField name="aStreet" label="Street" />
            <TextField name="aCity" label="City" />
            <TextField name="aStatus" label="Status" />

          </Stack>

          <Grid container justifyContent="flex-end"  sx={{ my: 3 }}>
            <Grid item xs={6}>
              <TextField name="astate" label="State"/>
            </Grid>
            <Grid item xs={6}>
              <TextField name="azipcode" label="Zip Code"/>
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
            onClick={() => closeEditMenu()}
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

                {/* {notFound && <TableNoData query={filterName} />} */}
              </TableBody>
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
