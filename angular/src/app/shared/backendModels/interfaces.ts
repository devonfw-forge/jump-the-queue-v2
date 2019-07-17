import { Direction, Status} from './enums';

export class Queue {
    id: number;
    modificationCounter: number;
    minAttentionTime: number;
    started: boolean;
    createdDate: number;
}

export class AccessCode {
    id: number;
    modificationCounter: number;
    code: string;
    uuid: string;
    createdDate: number;
    startTime?: number;
    endTime?: number;
    status: Status;
    queueId: number;
}

export class Owner {}

export class Sort {
    property: string;
    direction: Direction;
}

export class Pageable {
    pageNumber: number;
    pageSize: number;
    sort?: Sort[];
}

export class FilterAccessCode {
    createdDate?: number;
    status?: Status;
    queueId: number;
    pageable: Pageable;
}
