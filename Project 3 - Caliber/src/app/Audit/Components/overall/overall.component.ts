import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { Overallqc } from '../../../overallqc';
import { AuditService } from '../../Services/audit.service';
import { OverallService } from '../../Services/overall.service';

@Component({
	selector: 'app-overall',
	templateUrl: './overall.component.html',
	styleUrls: ['./overall.component.css']
})
export class OverallComponent implements OnInit {
	private overallqc: Overallqc;

	@ViewChild('qcBatchNotes') qcBatchNotes: ElementRef;

	showFloppy: boolean = true;
	showSaving: boolean = false;
	showCheck: boolean = false;
	showNotes: boolean = true;

	constructor(private _overallqcService: OverallService) { }

	ngOnInit() {
		this.overallqc = this._overallqcService.getter();
	}

	saveQCandTrainee() {

		console.log('clicked');

		this.showFloppy = !this.showFloppy;

		setTimeout(() => {
			console.log('processingNote');
			this.showNotes = false;
		}, 200);

		setTimeout(() => {
			console.log('showSaving');
			this.showSaving = true;
		}, 480);

		setTimeout(() => {
			console.log('showChecking');
			this.showSaving = false;
			this.showCheck = true;
		}, 2000);

		setTimeout(() => {
			console.log('showChecking');
			this.showSaving = false;
			this.showCheck = false;
			this.showFloppy = true;
		}, 4000);

	}

}