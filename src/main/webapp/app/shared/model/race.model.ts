import dayjs from 'dayjs';
import { IUser } from 'app/shared/model/user.model';
import { Distance } from 'app/shared/model/enumerations/distance.model';

export interface IRace {
  id?: number;
  raceName?: string;
  raceDate?: string | null;
  raceDistance?: Distance | null;
  user?: IUser | null;
}

export const defaultValue: Readonly<IRace> = {};
