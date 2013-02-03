package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import static org.apache.commons.lang3.StringUtils.join;

import java.io.File;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("serial")
class FileNameFormat extends Format {

	private static final Pattern FILES_PATTERN = Pattern.compile("\"(.*)\"");

	private File currentDirectory;

	public String format(Set<File> files) {
		return format(files, new StringBuffer(), new FieldPosition(0))
				.toString();
	}

	@Override
	public StringBuffer format(Object obj, StringBuffer buff, FieldPosition pos) {
		System.out.println("format: " + obj);// TODO println
		if (obj instanceof Set) {
			@SuppressWarnings("unchecked")
			Set<File> files = (Set<File>) obj;
			if (files.size() > 1) {
				formatFiles(files, buff);
			} else if (files.size() == 1) {
				Iterator<File> it = files.iterator();
				it.hasNext();
				formatFile(it.next(), buff);
			}
		}
		return buff;
	}

	private void formatFile(File file, StringBuffer buff) {
		buff.append(file.getName());
	}

	private void formatFiles(Set<File> files, StringBuffer buff) {
		List<String> names = new ArrayList<String>(files.size());
		for (File file : files) {
			names.add(String.format("\"%s\"", file.getName()));
		}
		buff.append(join(names, " "));
	}

	public void setCurrentDirectory(File file) {
		this.currentDirectory = file;
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		System.out.println("parse: " + source);// TODO println
		String[] split = StringUtils.split(source);
		Set<File> set = new TreeSet<File>();
		if (split.length == 1) {
			parseFile(split[0], set);
		} else if (split.length > 1) {
			parseFiles(split, set);
		}
		pos.setIndex(source.length());
		pos.setErrorIndex(-1);
		System.out.println(set);// TODO println
		return set;
	}

	private void parseFiles(String[] split, Set<File> set) {
		for (String name : split) {
			Matcher matcher = FILES_PATTERN.matcher(name);
			if (matcher.matches()) {
				name = matcher.group(1);
				File file = new File(currentDirectory, name);
				set.add(file);
			}
		}
	}

	private void parseFile(String string, Set<File> set) {
		File file = new File(string);
		if (!file.isAbsolute()) {
			file = new File(currentDirectory, string);
		}
		set.add(file);
	}

}
