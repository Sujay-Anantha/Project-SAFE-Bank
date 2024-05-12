import { useState } from 'react';
// import DOMPurify from 'dompurify';

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

async function createCustomer(cust) {
  console.log(cust)
  return fetch('http://localhost:8080/customer/create', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      id: 23,
      firstName: cust.firstName,
      lastName: cust.lastName,
      street: cust.street,
      city: cust.city,
      state: cust.state,
      zipcode: cust.zipcode,
      accounts: [],
    })
  })
 }

export default function SigninView() {
  const theme = useTheme();

  const router = useRouter();

  const [showPassword, setShowPassword] = useState(false);

  const [signinForm, setSigninForm] = useState({ firstName: '', lastName: '', street: '', city: '', state: '', zipcode: '', email: '', password: '', role: ''});
  
  const handleChange = (e) => {
    const {name, value} = e.target;
    setSigninForm({ ...signinForm, [name]: e.target.type === 'number' ? parseInt(value, 10) : value });
  };

  const handleSignin = () => {
    console.log(signinForm);
    // const sanitizedSigninForm=DOMPurify.sanitize(signinForm);
    const creatcust = createCustomer(signinForm);
    creatcust.then(response =>
      response.json().then(json => ({
        status: response.status,
        json
      })
    ))
    .then(({ status, json }) => {
      console.log({ status, json });
      // if (status === 201) {
      // }
      router.push('/login');
    })
  }

  const renderForm = (
    <>
      <Grid container justifyContent="flex-end" sx={{ my: 3 }}>
        <Grid item xs={4}>
          <TextField name="firstName" label="First Name" value={signinForm.firstName} onChange={handleChange}/>
        </Grid>
        <Grid item xs={4}>
          <TextField name="lastName" label="Last Name" value={signinForm.lastName} onChange={handleChange}/>
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

        <TextField name="street" label="Street" value={signinForm.street} onChange={handleChange}/>
        <TextField name="city" label="City" value={signinForm.city} onChange={handleChange}/>
      </Stack>

      <Grid container justifyContent="flex-end"  sx={{ my: 3 }}>
        <Grid item xs={6}>
          <TextField name="state" label="State" value={signinForm.state} onChange={handleChange}/>
        </Grid>
        <Grid item xs={6}>
          <TextField name="zipcode" label="Zip Code" value={signinForm.zipcode} onChange={handleChange} type="number"/>
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
