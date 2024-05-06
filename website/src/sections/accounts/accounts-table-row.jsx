import { useState } from 'react';
import DOMPurify from 'dompurify';
import PropTypes from 'prop-types';

import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import Select from '@mui/material/Select';
import Popover from '@mui/material/Popover';
import TableRow from '@mui/material/TableRow';
import MenuItem from '@mui/material/MenuItem';
import TextField from '@mui/material/TextField';
// import Checkbox from '@mui/material/Checkbox';
import TableCell from '@mui/material/TableCell';
import InputLabel from '@mui/material/InputLabel';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import FormControl from '@mui/material/FormControl';
import { Dialog, DialogTitle, DialogActions, DialogContent, DialogContentText } from '@mui/material';

import { visuallyHidden } from './utils';
import Label from '../../components/label';
import Iconify from '../../components/iconify';
import accountService from '../../service/accountService';


// ----------------------------------------------------------------------

export default function AccountsTableRow({
  selected,
  accNo,
  accName,
  address,
  dateOpened,
  status,
  handleClick,
}) {
  const [open, setOpen] = useState(null);

  const handleOpenMenu = (event) => {
    setOpen(event.currentTarget);
  };

  const handleCloseMenu = () => {
    setOpen(null);
  };

  const [edit, setEdit] = useState(false);

  const handleEditMenu = (event) => {
    setEdit(true);
  };

  const closeEditMenu = () => {
    setEdit(false);
  };

  const [del, setDel] = useState(null);

  const handleDeleteMenu = () => {
    setDel(true);
  };

  const closeDeleteMenu = () => {
    setDel(false);
  };

  const handleDelete = (accno) => {
    // TODO: call API to delete account
    accountService.deleteAccount(accno);
    closeDeleteMenu();
  }
  // const accountEditForm = (
  //   <>
  //     <Stack spacing={3}>
  //       <TextField name="accountName" label="Account Name" />
  //       <TextField name="aStreet" label="Street" />
  //       <TextField name="aCity" label="City" />
  //       <TextField name="aStatus" label="Status" />
  //       <TextField name="accountType" label="Account Type" />

  //     </Stack>

  //     <Grid container justifyContent="flex-end"  sx={{ my: 3 }}>
  //       <Grid item xs={6}>
  //         <TextField name="astate" label="State"/>
  //       </Grid>
  //       <Grid item xs={6}>
  //         <TextField name="azipcode" label="Zip Code"/>
  //       </Grid>
  //     </Grid>

  //     <Button
  //       fullWidth
  //       size="large"
  //       type="submit"
  //       variant="contained"
  //       color="inherit"
  //       // onClick={() => editAccount()}
  //     >
  //       Confirm
  //     </Button>
  //   </>
  // );

  const isAuthorized = () => {
    // TODO: get user role
    if (edit) {
      return visuallyHidden
    }
    return 'none'
  };

  const [editForm, setEditForm] = useState({ acctype: '', acct_name: '', astreet: '', acity: '', astate: '', azipcode: ''});
  
  const handleChange = (e) => {
    const {name, value} = e.target;
    setEditForm({ ...editForm, [name]: value });
  };

  const handleEdit = () => {
    console.log(editForm);
    const sanitizedEditForm=DOMPurify.sanitize(editForm);
    console.log(sanitizedEditForm);
    // TODO: call API to update account
    accountService.updateAccountDetails(sanitizedEditForm);
    closeEditMenu();
  }

  return (
    <>
      {/* <AccountEditForm
        open={!!edit}
        // anchorEl={open}
        // onClose={handleCloseMenu}
      /> */}
      <TableRow hover tabIndex={-1} role="checkbox" selected={selected}>
        {/* <TableCell padding="checkbox">
          <Checkbox disableRipple checked={selected} onChange={handleClick} />
        </TableCell> */}

        <TableCell component="th" scope="row">
          <Stack direction="row" alignItems="center" spacing={2}>
            <Typography variant="subtitle2" noWrap>
              {accNo}
            </Typography>
          </Stack>
        </TableCell>

        <TableCell>{accName}</TableCell>

        <TableCell>{address}</TableCell>

        <TableCell>{dateOpened}</TableCell>

        <TableCell>
          <Label color={(status === 'closed' && 'error') || 'success'}>{status}</Label>
        </TableCell>

        <TableCell align="right" sx={isAuthorized}>
          <IconButton onClick={handleOpenMenu}>
            <Iconify icon="eva:more-vertical-fill" />
          </IconButton>
        </TableCell>

      </TableRow>

      <Popover
        open={!!open}
        anchorEl={open}
        onClose={handleCloseMenu}
        anchorOrigin={{ vertical: 'top', horizontal: 'left' }}
        transformOrigin={{ vertical: 'top', horizontal: 'right' }}
        PaperProps={{
          sx: { width: 140 },
        }}
      >
        <MenuItem onClick={() => {handleEditMenu(); handleCloseMenu();}}>
          <Iconify icon="eva:edit-fill" sx={{ mr: 2 }} />
          Edit
        </MenuItem>

        <MenuItem onClick={() => {handleDeleteMenu(); handleCloseMenu();}} sx={{ color: 'error.main' }}>
          <Iconify icon="eva:trash-2-outline" sx={{ mr: 2 }} />
          Delete
        </MenuItem>
      </Popover>

      {/* edit menu */}
      <Dialog 
        open={!!edit}
        onClose={closeEditMenu}
      >
        <DialogTitle>Edit Account</DialogTitle>
        <DialogContent>
          <Stack spacing={2} paddingTop={2}>
            <TextField name="acct_name" label="Account Name" value={editForm.acct_name} onChange={handleChange}/>
            <TextField name="astreet" label="Street" value={editForm.astreet} onChange={handleChange}/>
            <TextField name="acity" label="City" value={editForm.acity} onChange={handleChange}/>
            <FormControl sx={{ minWidth: 120 }}>
              <InputLabel id="roleLabel">Account Type</InputLabel>
              <Select
                name="acctype"
                label="Account Type"
                // labelId="ac"
                value={editForm.acctype}
                onChange={handleChange}
              >
                <MenuItem value='checking'>Checking</MenuItem>
                <MenuItem value='savings'>Savings</MenuItem>
                <MenuItem value='loan'>Loan</MenuItem>
              </Select>
            </FormControl>
          </Stack>

          <Grid container justifyContent="flex-end"  sx={{ my: 3 }}>
            <Grid item xs={6}>
              <TextField name="astate" label="State" value={editForm.astate} onChange={handleChange}/>
            </Grid>
            <Grid item xs={6}>
              <TextField name="azipcode" label="Zip Code" value={editForm.azipcode} onChange={handleChange}/>
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
            onClick={() => handleEdit()}
          >
            Confirm
          </Button>
        </DialogActions>
      </Dialog>

      {/* delete confirm menu */}
      <Dialog 
        fullWidth
        open={!!del}
        onClose={closeDeleteMenu}
        // maxWidth="md"
        scroll="body"
      >
        <DialogTitle>Delete Account</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Are you sure you want to delete?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button
            fullWidth
            // type="submit"
            color="inherit"
            onClick={closeDeleteMenu}
          >
            Back
          </Button>

          <Button
            fullWidth
            // type="submit"
            variant="contained"
            color="inherit"
            onClick={() => handleDelete(accNo)}
          >
            Confirm
          </Button>
        </DialogActions>

      </Dialog>
      {/* <ConfirmBox
        open={!!del}
        closeDeleteMenu={closeDeleteMenu}
      /> */}
    </>
  );
}

AccountsTableRow.propTypes = {
  accNo: PropTypes.any,
  accName: PropTypes.any,
  handleClick: PropTypes.func,
  address: PropTypes.any,
  dateOpened: PropTypes.any,
  selected: PropTypes.any,
  status: PropTypes.string,
};
