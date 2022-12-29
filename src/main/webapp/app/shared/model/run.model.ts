import dayjs from 'dayjs';
import { IUser } from 'app/shared/model/user.model';

export interface IRun {
  id?: number;
  runName?: string;
  runDate?: string;
  distance?: number;
  time?: number;
  pace?: number | null;
  user?: IUser | null;
}

export const defaultValue: Readonly<IRun> = {};
