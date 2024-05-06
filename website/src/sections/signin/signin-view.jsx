import { useState } from 'react';
import DOMPurify from 'dompurify';

import Box from '@mui/material/Box';
import Link from '@mui/material/Link';
import Card from '@mui/material/Card';
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import LoadingButton from '@mui/lab/LoadingButton';
import { alpha, useTheme } from '@mui/material/styles';
import InputAdornment from '@mui/material/InputAdornment';
import { Select, MenuItem, InputLabel, FormControl  } from '@mui/material';

import { bgGradient } from '../../theme/css';
import { useRouter } from '../../routes/hooks';
import Iconify from '../../components/iconify';

// ----------------------------------------------------------------------

export default function SigninView() {
  const theme = useTheme();

  const router = useRouter();

  const [showPassword, setShowPassword] = useState(false);

  const [signinForm, setSigninForm] = useState({ cfname: '', clname: '', cstreet: '', ccity: '', cstate: '', czipcode: '', email: '', password: '', role: ''});
  
  const handleChange = (e) => {
    const {name, value} = e.target;
    setSigninForm({ ...signinForm, [name]: value });
  };

  const handleSignin = () => {
    console.log(signinForm);
    const sanitizedSigninForm=DOMPurify.sanitize(signinForm);
    console.log(sanitizedSigninForm);
    // TODO: all api to add customer info to db
    router.push('/login');
  }

  const renderForm = (
    <>
      <Grid container justifyContent="flex-end" sx={{ my: 3 }}>
        <Grid item xs={4}>
          <TextField name="cfname" label="First Name" value={signinForm.cfname} onChange={handleChange}/>
        </Grid>
        <Grid item xs={4}>
          <TextField name="clname" label="Last Name" value={signinForm.clname} onChange={handleChange}/>
        </Grid>
        <Grid item xs={4}>
          <FormControl sx={{ minWidth: 180 }}>
            <InputLabel id="roleLabel">Role</InputLabel>
            <Select
              name="role"
              label="Role"
              labelId="roleLabel"
              value={signinForm.role}
              onChange={handleChange}
            >
              <MenuItem value='customer'>Customer</MenuItem>
              <MenuItem value='employee'>Employee</MenuItem>
            </Select>
          </FormControl>
        </Grid>
      </Grid>

      <Stack spacing={2}  sx={{ my: 3 }}>
        <TextField name="email" label="Email address" value={signinForm.email} onChange={handleChange}/>

        <TextField
          name="password"
          label="Password"
          type={showPassword ? 'text' : 'password'}
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">
                <IconButton onClick={() => setShowPassword(!showPassword)} edge="end">
                  <Iconify icon={showPassword ? 'eva:eye-fill' : 'eva:eye-off-fill'} />
                </IconButton>
              </InputAdornment>
            ),
          }}
          value={signinForm.password}
          onChange={handleChange}
        />

        <TextField name="cstreet" label="Street" value={signinForm.cstreet} onChange={handleChange}/>
        <TextField name="ccity" label="City" value={signinForm.ccity} onChange={handleChange}/>
      </Stack>

      <Grid container justifyContent="flex-end"  sx={{ my: 3 }}>
        <Grid item xs={6}>
          <TextField name="cstate" label="State" value={signinForm.cstate} onChange={handleChange}/>
        </Grid>
        <Grid item xs={6}>
          <TextField name="czipcode" label="Zip Code" value={signinForm.czipcode} onChange={handleChange}/>
        </Grid>
      </Grid>

      <LoadingButton
        fullWidth
        size="large"
        type="submit"
        variant="contained"
        color="inherit"
        onClick={handleSignin}
      >
        Create Account
      </LoadingButton>
    </>
  );

  return (
    <Box
      sx={{
        ...bgGradient({
          color: alpha(theme.palette.background.default, 0.9),
          imgUrl: '/assets/background/overlay_4.jpg',
        }),
        overflow: "hidden",
        overflowY: "scroll",
      }}
    >

      <Stack alignItems="center" justifyContent="center" sx={{ height: 1 }}>
        <Card
          sx={{
            p: 5,
            width: 1,
            maxWidth: 720,
          }}
        >
          <Typography variant="h4">Sign in</Typography>

          <Typography variant="body2" sx={{ mt: 2, mb: 5 }}>
            Already have an account?
            <Link variant="subtitle2" sx={{ ml: 0.5 }} href="/login">
              Log In
            </Link>
          </Typography>

          {renderForm}
        </Card>
      </Stack>
    </Box>
  );
}
