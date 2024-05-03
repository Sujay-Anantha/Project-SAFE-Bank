import { sample } from 'lodash';
import { faker } from '@faker-js/faker';

// ----------------------------------------------------------------------

export const accounts = [...Array(1)].map((_) => ({
  id: faker.string.uuid(),
  // avatarUrl: `/assets/images/avatars/avatar_${index + 1}.jpg`,
  accNo: faker.finance.accountNumber(),
  accName: faker.finance.accountName(),
  address: faker.location.streetAddress(),
  dateOpened: faker.date.birthdate().toString(),
  status: sample(['active', 'closed']),
  // role: sample([
  //   'Leader',
  //   'Hr Manager',
  //   'UI Designer',
  //   'UX Designer',
  //   'UI/UX Designer',
  //   'Project Manager',
  //   'Backend Developer',
  //   'Full Stack Designer',
  //   'Front End Developer',
  //   'Full Stack Developer',
  // ]),
}));
