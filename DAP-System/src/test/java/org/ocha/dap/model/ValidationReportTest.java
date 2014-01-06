package org.ocha.dap.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.ocha.dap.model.validation.ValidationReport;
import org.ocha.dap.model.validation.ValidationReportEntry;
import org.ocha.dap.model.validation.ValidationStatus;
import org.ocha.dap.persistence.entity.ckan.CKANDataset.Type;

public class ValidationReportTest {

	@Test
	public void testAddEntry() {
		final ValidationReport report = new ValidationReport(Type.DUMMY);

		Assert.assertEquals(ValidationStatus.SUCCESS, report.getStatus());
		Assert.assertTrue(report.isNotInError());
		Assert.assertEquals(0, report.getEntries().size());

		report.addEntry(ValidationStatus.WARNING, "A warning");

		Assert.assertEquals(ValidationStatus.WARNING, report.getStatus());
		Assert.assertTrue(report.isNotInError());
		Assert.assertEquals(1, report.getEntries().size());

		report.addEntry(ValidationStatus.SUCCESS, "A success");

		Assert.assertEquals(ValidationStatus.WARNING, report.getStatus());
		Assert.assertTrue(report.isNotInError());
		Assert.assertEquals(2, report.getEntries().size());

		report.addEntry(ValidationStatus.ERROR, "An error");

		Assert.assertEquals(ValidationStatus.ERROR, report.getStatus());
		Assert.assertFalse(report.isNotInError());
		Assert.assertEquals(3, report.getEntries().size());

		report.addEntry(ValidationStatus.WARNING, "Another warning");

		Assert.assertEquals(ValidationStatus.ERROR, report.getStatus());
		Assert.assertFalse(report.isNotInError());
		Assert.assertEquals(4, report.getEntries().size());

		report.addEntry(ValidationStatus.SUCCESS, "Another success");

		Assert.assertEquals(ValidationStatus.ERROR, report.getStatus());
		Assert.assertFalse(report.isNotInError());
		Assert.assertEquals(5, report.getEntries().size());
	}

	@Test
	public void testAddEntries() {
		final ValidationReport report = new ValidationReport(Type.DUMMY);

		Assert.assertEquals(ValidationStatus.SUCCESS, report.getStatus());
		Assert.assertTrue(report.isNotInError());
		Assert.assertEquals(0, report.getEntries().size());

		{
			final ValidationReportEntry entry1 = new ValidationReportEntry(ValidationStatus.SUCCESS, "a success");
			final ValidationReportEntry entry2 = new ValidationReportEntry(ValidationStatus.SUCCESS, "a success");
			final List<ValidationReportEntry> entries = new ArrayList<>();
			entries.add(entry1);
			entries.add(entry2);
			report.addEntries(entries);

			Assert.assertEquals(ValidationStatus.SUCCESS, report.getStatus());
			Assert.assertTrue(report.isNotInError());
			Assert.assertEquals(2, report.getEntries().size());
		}

		{
			final ValidationReportEntry entry1 = new ValidationReportEntry(ValidationStatus.WARNING, "a warning");
			final ValidationReportEntry entry2 = new ValidationReportEntry(ValidationStatus.SUCCESS, "a success");
			final List<ValidationReportEntry> entries = new ArrayList<>();
			entries.add(entry1);
			entries.add(entry2);
			report.addEntries(entries);

			Assert.assertEquals(ValidationStatus.WARNING, report.getStatus());
			Assert.assertTrue(report.isNotInError());
			Assert.assertEquals(4, report.getEntries().size());
		}

		{
			final ValidationReportEntry entry1 = new ValidationReportEntry(ValidationStatus.SUCCESS, "a success");
			final ValidationReportEntry entry2 = new ValidationReportEntry(ValidationStatus.SUCCESS, "a success");
			final List<ValidationReportEntry> entries = new ArrayList<>();
			entries.add(entry1);
			entries.add(entry2);
			report.addEntries(entries);

			Assert.assertEquals(ValidationStatus.WARNING, report.getStatus());
			Assert.assertTrue(report.isNotInError());
			Assert.assertEquals(6, report.getEntries().size());
		}

		{
			final ValidationReportEntry entry1 = new ValidationReportEntry(ValidationStatus.WARNING, "a warning");
			final ValidationReportEntry entry2 = new ValidationReportEntry(ValidationStatus.ERROR, "an error");
			final List<ValidationReportEntry> entries = new ArrayList<>();
			entries.add(entry1);
			entries.add(entry2);
			report.addEntries(entries);

			Assert.assertEquals(ValidationStatus.ERROR, report.getStatus());
			Assert.assertFalse(report.isNotInError());
			Assert.assertEquals(8, report.getEntries().size());
		}

		{
			final ValidationReportEntry entry1 = new ValidationReportEntry(ValidationStatus.WARNING, "a warning");
			final ValidationReportEntry entry2 = new ValidationReportEntry(ValidationStatus.SUCCESS, "a success");
			final List<ValidationReportEntry> entries = new ArrayList<>();
			entries.add(entry1);
			entries.add(entry2);
			report.addEntries(entries);

			Assert.assertEquals(ValidationStatus.ERROR, report.getStatus());
			Assert.assertFalse(report.isNotInError());
			Assert.assertEquals(10, report.getEntries().size());
		}
	}

}
