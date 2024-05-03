import PropTypes from 'prop-types';

import Button from '@mui/material/Button';
import { Dialog, DialogTitle, DialogActions, DialogContent, DialogContentText } from '@mui/material';

export default function ConfirmBox({
    del, 
    closeDeleteMenu
}) {

    return (
      <Dialog 
        fullWidth
        open={del}
        onClose={closeDeleteMenu}
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
            onClick={() => closeDeleteMenu()}
          >
            Confirm
          </Button>
        </DialogActions>

      </Dialog>
    )
};

ConfirmBox.propTypes = {
    del: PropTypes.bool,
    closeDeleteMenu: PropTypes.func,
};