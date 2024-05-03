import { Helmet } from 'react-helmet-async';

import { AccountsView } from 'src/sections/accounts/view';

// ----------------------------------------------------------------------

export default function AccountPage() {
  return (
    <>
      <Helmet>
        <title> Accounts </title>
      </Helmet>

      <AccountsView />
    </>
  );
}
