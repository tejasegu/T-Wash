export class Bookings {
    constructor(
        public id:number,
        public bookedon:string,
        public scheduledlater:boolean,
        public scheduleddate:string,
        public userid:string,
        public username:string,
        public number:number,
        public carname:string,
        public carnumber:string,
        public area:string,
        public location:string,
        public washerid:string,
        public washername:string,
        public washernumber:number,
        public packages:string,
        public addon:string,
        public orderstatus:string,
        public paymenttype:string,
        public transactionid:string,
        public paymentstatus:string,
        public amount:number
      ) {}
}
