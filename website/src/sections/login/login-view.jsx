import { useState } from 'react';
import DOMPurify from 'dompurify';

import Box from '@mui/material/Box';
import Link from '@mui/material/Link';
import Card from '@mui/material/Card';
import Stack from '@mui/material/Stack';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import TextField from '@mui/material/TextField';
import InputLabel from '@mui/material/InputLabel';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import LoadingButton from '@mui/lab/LoadingButton';
import FormControl from '@mui/material/FormControl';
import { alpha, useTheme } from '@mui/material/styles';
import InputAdornment from '@mui/material/InputAdornment';

import { bgGradient } from '../../theme/css';
import { useRouter } from '../../routes/hooks';
import Iconify from '../../components/iconify';



// ----------------------------------------------------------------------

export default function LoginView() {
  const theme = useTheme();

  const router = useRouter();

  const [showPassword, setShowPassword] = useState(false);

  const [loginForm, setLoginForm] = useState({ email: '', password: '', role: ''});

  const handleChange = (e) => {
    const {name, value} = e.target;
    setLoginForm({ ...loginForm, [name]: value });
  }

  const authentication = (email, password) => {
    // const raw = apiClient.request(`/db/get?user=${user}`, 'GET', {})
    // if (raw.status === 200 && raw.data.password === password) {
    //   return true;
    // }
    // TODO: call API to check login info is correct
    
    if (DOMPurify.sanitize(email)==="1" && DOMPurify.sanitize(password)==="2") {
      router.push('/accounts');
    }
    else {
      alert("Wrong username or password")
    }
  }

  const renderForm = (
    <>
      <Stack spacing={3}>
        <TextField name="email" label="Email address" value={loginForm.email} onChange = {handleChange} />

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
          value={loginForm.password}
          onChange = {handleChange}
        />

        <FormControl sx={{ m: 1, minWidth: 120 }}>
          <InputLabel id="roleLabel">Role</InputLabel>
          <Select
            name="role"
            label="Role"
            labelId="roleLabel"
            value={loginForm.role}
            onChange={handleChange}
          >
            <MenuItem value='customer'>Customer</MenuItem>
            <MenuItem value='employee'>Employee</MenuItem>
          </Select>
        </FormControl>
      </Stack>

      {/* <Stack direction="row" alignItems="center" justifyContent="flex-end" sx={{ my: 3 }}>
        <Link variant="subtitle2" underline="hover">
          Forgot password?
        </Link>
      </Stack> */}

      <LoadingButton
        fullWidth
        size="large"
        type="submit"
        variant="contained"
        color="inherit"
        sx={{ my: 3 }}
        onClick={() => authentication(loginForm.email, loginForm.password)}
      >
        Login
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
        height: 1,
      }}
    >

      <Stack alignItems="center" justifyContent="center" sx={{ height: 1 }}>
        <Card
          sx={{
            p: 5,
            width: 1,
            maxWidth: 420,
          }}
        >
          <Typography variant="h4">Log in</Typography>

          <Typography variant="body2" sx={{ mt: 2, mb: 5 }}>
            Don’t have an account?
            <Link variant="subtitle2" sx={{ ml: 0.5 }} href="/signin">
              Get started
            </Link>
          </Typography>

          {renderForm}
        </Card>
      </Stack>
    </Box>
  );
}
