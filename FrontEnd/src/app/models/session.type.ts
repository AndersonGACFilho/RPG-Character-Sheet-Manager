export type ISession = {
  id: number | null;
  name: string;
  description: string;
};

export type ISessionList = {
  id: number;
  name: null | string;
  description: null | string;
  master: IMaster;
};

export type IMaster = {
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
};
